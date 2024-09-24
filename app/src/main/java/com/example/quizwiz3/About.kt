package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

    class About : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_about)

            // Setup toolbar
            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.title = "About QuizWiz"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            // Handle Logout Button click
            val btnLogout: Button = findViewById(R.id.btnLogout)
            btnLogout.setOnClickListener {
                // Log out the user (you can customize this according to your authentication flow)
                logoutUser()
            }
        }

        private fun logoutUser() {
            // Perform the logout logic here
            // This can be signing out of Firebase Auth or clearing shared preferences, etc.
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
