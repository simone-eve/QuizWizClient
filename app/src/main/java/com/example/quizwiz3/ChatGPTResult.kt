package com.example.quizwiz3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
    private val stringAPIKey = "AIzaSyAC0kKEZg_UjPUcKIA93qnEoPSdudgvalw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_gptresult)
        val question = intent.getStringExtra("selectedQuestion") ?: "Default"

        textViewAnswer = findViewById(R.id.tvQResult)
        textViewQuestion = findViewById(R.id.tvQuestion)

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



}