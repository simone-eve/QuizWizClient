package com.example.quizwiz3


import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import api.DataModel
import api.RetrofitAPI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
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
    private lateinit var tvRedirectLogin: TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso!!)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
//        if (acct != null) {
//            navigateToSecondActivity()
//        }
        val googleSignInButton = findViewById<SignInButton>(R.id.signIn)
        googleSignInButton.setOnClickListener { signIn() }

        etEmail = findViewById(R.id.etEmailAddress)
        etPasword = findViewById(R.id.etPassword)
        etName = findViewById(R.id.etName)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        // initializing Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
             signUpUser()
        }


        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    private fun signUpUser() {
        val email = etEmail.text.toString()
        val password = etPasword.text.toString()

        if (email.isBlank() || password.isBlank() ) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        } else {

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                       
                    } else {
                        Toast.makeText(this, "Sign Up Failed!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

    fun signIn() {
        val signInIntent = gsc!!.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                navigateToSecondActivity()
                Toast.makeText(applicationContext, "Signed In", Toast.LENGTH_SHORT)
                    .show()


            } catch (e: ApiException) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun navigateToSecondActivity() {
        finish()
        val intent: Intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun postData(name: String, email: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://localhost:7089/api/PostRegister")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        // Creating an instance of the API interface
//        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
//
//        // Passing data to the data model
//        val model = DataModel(name, email)
//
//        // Creating a POST request
//        val call: Call<DataModel> = retrofitAPI.createPost(model)
//
//        // Executing the request
//        call.enqueue(object : Callback<DataModel> {
//            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
//                // Handle the response from the API
//                Toast.makeText(this@MainActivity, "Data added to API", Toast.LENGTH_SHORT).show()
////                nameEdt.text.clear()
////                jobEdt.text.clear()
//
//                val responseFromAPI = response.body()
//                val responseString = "Response Code : ${response.code()}\nName : ${responseFromAPI?.name}\nEmail : ${responseFromAPI?.email}\n"
////                        "FirebaseUUID : ${responseFromAPI?.firebaseUUID}"
//                Toast.makeText(applicationContext, "${responseString}", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//            override fun onFailure(call: Call<DataModel>, t: Throwable) {
//                // Handle errors
//                Toast.makeText(applicationContext, "Error found is: ${t.message}", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        })
    }


}
