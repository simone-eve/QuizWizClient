package com.example.quizwiz3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.google.androidgamesdk.gametextinput.Settings

    class GameSettings  : AppCompatActivity() {

        // Declare UI components
        private lateinit var toolbar: Toolbar
        private lateinit var saveButton: Button
        private lateinit var logoutButton: Button
        private lateinit var notificationsSwitch: Switch
        private lateinit var soundMusicSwitch: Switch
        private lateinit var modeSwitch: Switch
        private lateinit var languageSpinner: Spinner
        private lateinit var notificationsButton: MaterialButton
        private lateinit var soundMusicButton: MaterialButton
        private lateinit var modeButton: MaterialButton

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_settings) // Set your XML layout

            // Initialize UI components
            toolbar = findViewById(R.id.toolbar)
            saveButton = findViewById(R.id.btnProfileSettings)
            logoutButton = findViewById(R.id.btnLogout)
            notificationsSwitch = findViewById(R.id.NotificationsSwitch)
            soundMusicSwitch = findViewById(R.id.SoundMusicSwitch)
            modeSwitch = findViewById(R.id.ModeSwitch)
            languageSpinner = findViewById(R.id.languageSpinner)
            notificationsButton = findViewById(R.id.btnNotificationsSettings)
            soundMusicButton = findViewById(R.id.btnSoundMusic)
            modeButton = findViewById(R.id.btnMode)

            // Set up the toolbar
            setSupportActionBar(toolbar)

            // Set up click listeners for buttons
            saveButton.setOnClickListener {
                // Save settings action
                Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show()
                // TODO: Implement save logic
            }

            logoutButton.setOnClickListener {
                // Handle logout action
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
                // TODO: Implement logout functionality
            }

            // Set up switches for notifications, sound/music, and mode
            notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
                val message = if (isChecked) "Notifications Enabled" else "Notifications Disabled"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                // TODO: Implement notification switch logic
            }

            soundMusicSwitch.setOnCheckedChangeListener { _, isChecked ->
                val message = if (isChecked) "Sound Enabled" else "Sound Disabled"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                // TODO: Implement sound/music switch logic
            }

            modeSwitch.setOnCheckedChangeListener { _, isChecked ->
                val mode = if (isChecked) "Dark Mode" else "Light Mode"
                Toast.makeText(this, "Switched to $mode", Toast.LENGTH_SHORT).show()
                // TODO: Implement light/dark mode switch logic
            }

            // Set up language spinner
            val languages = arrayOf("English", "Afrikaans", "Zulu")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
            languageSpinner.adapter = adapter

            languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                    val selectedLanguage = languages[position]
                    Toast.makeText(this@GameSettings, "Selected: $selectedLanguage", Toast.LENGTH_SHORT).show()
                    // TODO: Implement language selection logic
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Do nothing
                }
            }
        }
    }
