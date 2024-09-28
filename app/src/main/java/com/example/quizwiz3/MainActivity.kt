package com.example.quizwiz3

import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var gso: GoogleSignInOptions? = null
    var gsc: GoogleSignInClient? = null
    private lateinit var etEmail: EditText
    private lateinit var etPasword: EditText
    private lateinit var etName: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnLogout: Button
    private lateinit var tvRedirectLogin: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_client_id))
            .requestEmail()
            .build();
        gsc = GoogleSignIn.getClient(this, gso!!)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
//        if (acct != null) {
//            navigateToSecondActivity()
//        }
        val googleSignInButton = findViewById<SignInButton>(R.id.signIn)
        googleSignInButton.setOnClickListener {signIn() }


        etEmail = findViewById(R.id.etEmailAddress)
        btnLogout = findViewById<Button>(R.id.logout)
        etPasword = findViewById(R.id.etPassword)
        etName = findViewById(R.id.etName)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        // initializing Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val user = auth.currentUser
            val displayName = user?.displayName ?: "User"

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }

        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val password = etPasword.text.toString()
        val name = etName.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    val firebaseUUID = firebaseUser?.uid.toString()
                    saveUserDetails(name, email, firebaseUUID)

                } else {
                    Toast.makeText(this, "Sign Up Failed! Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveUserDetails(name: String, email: String, firebaseUUID: String) {
        val apiService = RetrofitClient.instance.create(QuizApiService::class.java)

        // Create a UserDetails object with the provided details
        val userDetails = UserDetails(name, email, firebaseUUID)

        // Make the API call to post user details
        apiService.postUserDetails(userDetails).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    Toast.makeText(this@MainActivity, "Sign up successful!", Toast.LENGTH_SHORT).show()
                    navigateToSecondActivity()
                } else {
                    // Handle error response
                    Toast.makeText(this@MainActivity, "Failed to sign up. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun signIn() {
        gsc?.signOut()?.addOnCompleteListener {
            val signInIntent = gsc!!.signInIntent
            startActivityForResult(signInIntent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
               val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)

            } catch (e: ApiException) {
                Toast.makeText(applicationContext,
                "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    val displayName = user?.displayName.toString()
                    val email = user?.email.toString()
                    val firebaseUUID = user?.uid.toString()
                    saveUserDetails(displayName, email, firebaseUUID)

                } else {
                    // Sign in failed
                    Toast.makeText(this, "Firebase Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun navigateToSecondActivity() {
        finish()
        val intent: Intent = Intent(this@MainActivity, Dashboard::class.java)
        startActivity(intent)
    }
}
