package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PlayerSelection : AppCompatActivity() {

    private lateinit var singlebtn: ImageButton
    private lateinit var multibtn: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        val singlebtn: ImageButton = findViewById(R.id.singlebtn)
        val multibtn: ImageButton = findViewById(R.id.multibtn)


        // Set an OnClickListener on the button

        singlebtn.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        multibtn.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

    }
}