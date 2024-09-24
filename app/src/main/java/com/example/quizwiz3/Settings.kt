package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toolbar

class Settings : AppCompatActivity() {

    // Declare variables for the UI components
    private lateinit var toolbar: Toolbar
    private lateinit var profileSettingsButton: Button
    private lateinit var gameSettingsButton: Button
    private lateinit var helpSupportButton: Button
    private lateinit var aboutButton: Button
    private lateinit var logoutButton: Button
    private lateinit var imageView: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set your XML layout here

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar)
        profileSettingsButton = findViewById(R.id.btnProfileSettings)
        gameSettingsButton = findViewById(R.id.btnGameSettings)
        helpSupportButton = findViewById(R.id.btnHelpSupport)
        aboutButton = findViewById(R.id.btnAbout)
        logoutButton = findViewById(R.id.btnLogout)
        imageView = findViewById(R.id.imageView)

        // Set up the toolbar
        setSupportActionBar(toolbar)

        // Set click listeners for buttons
        profileSettingsButton.setOnClickListener {
            // Navigate to Profile Settings
            val intent = Intent(this, MultipleChoice::class.java)
            startActivity(intent)
        }

        gameSettingsButton.setOnClickListener {
            // Navigate to Game Settings
            val intent = Intent(this, GameSettings::class.java)
            startActivity(intent)
        }

        helpSupportButton.setOnClickListener {
            // Navigate to Help and Support
            val intent = Intent(this, HelpSupport::class.java)
            startActivity(intent)
        }

        aboutButton.setOnClickListener {
            // Navigate to About the App
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            // Handle logout action
            val intent = Intent(this, MultipleChoice::class.java)
            startActivity(intent)
        }
    }

    private fun setSupportActionBar(toolbar: Toolbar?) {

    }
}
