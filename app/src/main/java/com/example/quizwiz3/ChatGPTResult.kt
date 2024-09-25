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

    private lateinit var textView: TextView
    private val stringAPIKey = "AIzaSyAC0kKEZg_UjPUcKIA93qnEoPSdudgvalw"
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_gptresult)

        textView = findViewById(R.id.textView)
        button = findViewById<Button>(R.id.chatgptButton)
        button.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-1.5-flash",
                        apiKey = stringAPIKey
                    )
                    val prompt = "How many planets are in our solar system"
                    val response = generativeModel.generateContent(prompt)
                    print(response.text)
                    textView.text = response.text
                    button.tooltipText = response.text
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@ChatGPTResult, "Error generating content", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }



}