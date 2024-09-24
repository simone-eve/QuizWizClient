package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HelpSupport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_support)

        // Setup toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Help & Support"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setup contact support details
        val contactEmail: TextView = findViewById(R.id.textView2)
        contactEmail.text = "Email Address: quizwiz@gmail.com\nContact Number: 081 264 9953"

        // Handle Submit button click
        val btnSubmit: Button = findViewById(R.id.btnProfileSettings)
        btnSubmit.setOnClickListener {
            // Logic for submitting feedback or question
            submitFeedback()
            }

           // Handle Logout button click
        val btnLogout: Button = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // Log out the user (modify according to your authentication method)
            logoutUser()
            }
        }

        private fun submitFeedback() {
            // Add logic to handle feedback submission, e.g., sending data to Firebase or your backend
        }

        private fun logoutUser() {
            // Perform the logout logic here
            // For example, if using Firebase Auth, you could call FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
