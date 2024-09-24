package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MultipleChoice : AppCompatActivity() {

    private lateinit var nextbtn: Button
    private lateinit var backbtn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)

        val nextbtn: Button = findViewById(R.id.nextbtn)
        val backbtn: Button = findViewById(R.id.backbtn)

        nextbtn.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, TrueorFalse::class.java)
            startActivity(intent)
        }

        backbtn.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

    }
}