package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HelpSupport : AppCompatActivity() {

    private lateinit var btnSubmitFeedback: Button
    private lateinit var editTextFeedback: EditText
    private lateinit var textViewFAQ: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_support) // Ensure you are linking the correct XML layout file

        // Initialize views
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback)
        editTextFeedback = findViewById(R.id.editTextFeedback)
        textViewFAQ = findViewById(R.id.textViewFAQ)

        // Set up FAQ text
        textViewFAQ.text = "Frequently Asked Questions:\n1. How to reset my password?\n2. How to contact support?\n3. How to change my email?"

        // Set OnClickListener for submit feedback button
        btnSubmitFeedback.setOnClickListener {
            submitFeedback()
        }
    }

    private fun submitFeedback() {
        val feedback = editTextFeedback.text.toString()

        if (feedback.isNotEmpty()) {
            // Logic to save feedback (e.g., send to server or save locally)

            // Show confirmation message
            Toast.makeText(this, "FAQ and feedback submitted!", Toast.LENGTH_SHORT).show()

            // Optionally, clear the feedback input
            editTextFeedback.text.clear()
        } else {
            Toast.makeText(this, "Please enter your feedback.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logoutUser() {
        // Perform the logout logic here
        // Example: Firebase Auth sign out
        // FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
