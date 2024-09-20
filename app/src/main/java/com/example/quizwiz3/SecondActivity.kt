package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import api.DataModel
import api.RetrofitAPI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SecondActivity : AppCompatActivity() {
    var gso: GoogleSignInOptions? = null
    var gsc: GoogleSignInClient? = null

    private lateinit var signOutBtn: Button
    private lateinit var email: TextView
    private lateinit var nameTest: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        nameTest = findViewById<TextView>(R.id.name)
        email = findViewById<TextView>(R.id.email)
        signOutBtn = findViewById(R.id.signout)
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso!!)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName ?: "Unknown Name" // Provide a default value in case of null
            val personEmail = acct.email ?: "Unknown Email"
          //  name.setText(personName)
            email.setText(personEmail)
            postData(personName, personEmail)
        }
        signOutBtn.setOnClickListener(View.OnClickListener { signOut() })
    }

    private fun postData(name: String, email: String) {
        // Display a toast to indicate the start of the method
        Toast.makeText(this, "postData called with Name: $name, Email: $email", Toast.LENGTH_SHORT).show()

        // Creating a Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:5200/api/PostRegister/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Toast.makeText(this, "Retrofit instance created", Toast.LENGTH_SHORT).show()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        Toast.makeText(this, "Retrofit API instance created", Toast.LENGTH_SHORT).show()


        val model = DataModel(name, email)

        Toast.makeText(this, "DataModel created with Name: ${model.name}, Email: ${model.email}", Toast.LENGTH_SHORT).show()

        val call: Call<DataModel> = retrofitAPI.createPost(model)

        Toast.makeText(this, "POST request created, calling enqueue...", Toast.LENGTH_SHORT).show()

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {

                if (response.isSuccessful) {
                    Toast.makeText(this@SecondActivity, "Received response from API", Toast.LENGTH_SHORT).show()


                    val responseFromAPI = response.body()
                    val responseString = "Response Code: ${response.code()}\n" +
                            "Name: ${responseFromAPI?.name}\n" +
                            "Email: ${responseFromAPI?.email}"
                    Toast.makeText(applicationContext, "Response: $responseString", Toast.LENGTH_SHORT).show()

                } else {
                    // Handle cases where the response is not successful (e.g., 4xx or 5xx errors)
                    Toast.makeText(this@SecondActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    nameTest.setText(response.code())
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(this@SecondActivity, "Error Body: $errorBody", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                // Handle errors
                Toast.makeText(applicationContext, "API call failed: ${t.message}", Toast.LENGTH_LONG).show()
                nameTest.setText(t.message)
                // Log the full stack trace for debugging
                t.printStackTrace()
            }
        })
    }

    fun navigateToSecondActivity() {
        finish()
        val intent: Intent = Intent(this@SecondActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun signOut() {
        gsc!!.signOut().addOnCompleteListener {
            finish()
            startActivity(Intent(this@SecondActivity, MainActivity::class.java))
        }
    }
}