package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.quizwiz3.api.ApiClient
import com.example.quizwiz3.api.ApiService
import com.example.quizwiz3.api.GoogleLoginModel
import com.example.quizwiz3.api.LoginModel
import com.example.quizwiz3.api.LoginRequest
import com.example.quizwiz3.api.LoginResponse


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Email and password login
        val emailInput = findViewById<EditText>(R.id.etEmailAddress)
        val passwordInput = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginWithEmailPassword(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Google Sign-In button
        val googleSignInButton = findViewById<SignInButton>(R.id.ssoLogin)
        googleSignInButton.setOnClickListener {
            signInWithGoogle()
        }

        // Redirect to SignUp activity
        val signUpRedirect = findViewById<TextView>(R.id.tvRedirectSignUp)
        signUpRedirect.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun loginWithEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    sendLoginRequestToApi(user, email, password)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result from Google Sign-In
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // If account is not null, proceed with Firebase authentication
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                } else {
                    Toast.makeText(this, "Google sign-in unsuccessful", Toast.LENGTH_SHORT).show()
                }
            } catch (e: ApiException) {
                Log.w("LoginActivity", "Google sign in failed", e)
                Toast.makeText(this, "Google sign-in failed1", Toast.LENGTH_SHORT).show() // Inform the user
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Successfully signed in, redirect to MainActivity
                    sendLoginRequestToApi(user, null, null)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Login failed, show a toast message
                    Toast.makeText(this, "Google Sign-In failed2", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendLoginRequestToApi(user: FirebaseUser?, email: String?, password: String?) {
        val retrofit = ApiClient.getClient()
        val apiService = retrofit.create(ApiService::class.java)

        val call = if (user != null && email == null && password == null) {
            apiService.loginWithGoogle(GoogleLoginModel(user.uid))
        } else {
            apiService.loginWithEmailPassword(LoginModel(email!!, password!!))
        }

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Toast.makeText(this@LoginActivity, loginResponse?.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "API request failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
