package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.androidgamesdk.gametextinput.Settings

class HelpSupport : AppCompatActivity() {

    private lateinit var btnSubmitFeedback: Button
    private lateinit var editTextFeedback: EditText
    private lateinit var textViewFAQ: TextView
    private lateinit var btnBackSettings: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_support) // Ensure you are linking the correct XML layout file

        // Initialize views
        btnBackSettings = findViewById(R.id.btnBackSettings)
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback)
        editTextFeedback = findViewById(R.id.editTextFeedback)
        textViewFAQ = findViewById(R.id.textViewFAQ)

        // Back to Settings Menu Button
        btnBackSettings = findViewById(R.id.btnBackSettings)
        btnBackSettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
        // Set up FAQ text
        textViewFAQ.text = "Frequently Asked Questions:\n1. How to reset my password?\n2. How to contact support?\n3. How to change my email?"

        // Set OnClickListener for submit feedback button
        btnSubmitFeedback.setOnClickListener {
            submitFeedback()
        }


        // Remove the unnecessary Intent that restarts this activity
        // val intent = Intent(this, HelpSupport::class.java)
        // startActivity(intent)
    }

    private fun submitFeedback() {
        val feedback = editTextFeedback.text.toString()

        if (feedback.isNotEmpty()) {
            // Here, you can add your logic to save the feedback (e.g., send to server, save locally, etc.)

            // Show a confirmation message
            Toast.makeText(this, "FAQ and feedback submitted!", Toast.LENGTH_SHORT).show()

            // Optionally, clear the feedback input
            editTextFeedback.text.clear()
        } else {
            Toast.makeText(this, "Please enter your feedback.", Toast.LENGTH_SHORT).show()
        }
    }
}
