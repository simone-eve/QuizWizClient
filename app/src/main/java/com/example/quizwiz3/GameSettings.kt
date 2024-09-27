package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.google.androidgamesdk.gametextinput.Settings

    class GameSettings  : AppCompatActivity() {

        private lateinit var btnBackSettings: Button

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_game_settings)

            btnBackSettings = findViewById(R.id.btnProfileSettings)


            val intent = Intent(this, GameSettings::class.java)
            startActivity(intent)

            // Set OnClickListeners for the buttons
            btnBackSettings.setOnClickListener {
                // Start Profile Activity
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
            }
        }

    }