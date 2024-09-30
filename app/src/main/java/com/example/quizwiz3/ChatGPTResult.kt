package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class ChatGPTResult : AppCompatActivity() {

    private lateinit var textViewAnswer: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var backbtn: Button;
    private val stringAPIKey = "AIzaSyAC0kKEZg_UjPUcKIA93qnEoPSdudgvalw"

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_gptresult)
        val question = intent.getStringExtra("selectedQuestion") ?: "Default"
        val optionsList = intent.getStringArrayListExtra("optionsList")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val backbtn: Button = findViewById(R.id.backbtn)

        // Handle Back button to go to PlayerSelection
        backbtn.setOnClickListener {
            val intent = Intent(this, Results::class.java)
            startActivity(intent)
        }
        textViewAnswer = findViewById(R.id.tvQResult)
        textViewQuestion = findViewById(R.id.tvQuestion)
        val options = optionsList?.joinToString(", ")
        val fullQuestion = question + options

        if(optionsList == null)
        {
            lifecycleScope.launch {
                try {
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-1.5-flash",
                        apiKey = stringAPIKey
                    )
                    val prompt = question
                    val response = generativeModel.generateContent(prompt)
                    print(response.text)
                    textViewAnswer.text = "\n " + response.text
                    textViewQuestion.text = "\n " + prompt
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@ChatGPTResult, "Error generating content", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            lifecycleScope.launch {
                try {
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-1.5-flash",
                        apiKey = stringAPIKey
                    )
                    val prompt = fullQuestion
                    val response = generativeModel.generateContent(prompt)
                    print(response.text)
                    textViewAnswer.text = "\n " + response.text
                    textViewQuestion.text = "\n " + fullQuestion
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@ChatGPTResult, "Error generating content", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                startActivity(Intent(this, Profile::class.java))
                return true
            }

            R.id.dashboard -> {
                startActivity(Intent(this, Dashboard::class.java))
                return true
            }

            R.id.settings -> {
                startActivity(Intent(this, com.example.quizwiz3.Settings::class.java))
                return true
            }

            R.id.helpsupport -> {
                startActivity(Intent(this, HelpSupport::class.java))
                return true
            }

            R.id.about -> {
                startActivity(Intent(this, About::class.java))
                return true
            }

            R.id.logout -> {
                Toast.makeText(this, "Logged Out acti", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Logout::class.java))

            }
        }
        return super.onOptionsItemSelected(item)
    }



}