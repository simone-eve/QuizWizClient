package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Dashboard : AppCompatActivity() {

    //declare variables
    private lateinit var Backbtn2: Button
    private lateinit var animalbtn: ImageButton
    private lateinit var btnfood: ImageButton
    private lateinit var btnmusic: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val Backbtn2: Button = findViewById(R.id.Backbtn2)

        val animalbtn: ImageButton = findViewById(R.id.animalbtn)

        val btnfood: ImageButton = findViewById(R.id.btnfood)

        val btndisney: ImageButton = findViewById(R.id.btndisney)

        val btnmusic: ImageButton = findViewById(R.id.btnmusic)

        val btntvshows: ImageButton = findViewById(R.id.btntvshows)


        val btnhistory: ImageButton = findViewById(R.id.btnhistory)

        Backbtn2.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, PlayerSelection::class.java)
            startActivity(intent)
        }

        animalbtn.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, MultipleChoice::class.java)
            startActivity(intent)
        }


        btnfood.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, MultipleChoice::class.java)
            startActivity(intent)
        }
        btnmusic.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, MultipleChoice::class.java)
            startActivity(intent)
        }
        btndisney.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, TrueorFalse::class.java)
            startActivity(intent)
        }
        btntvshows.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, TrueorFalse::class.java)
            startActivity(intent)
        }
        btnmusic.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, MultipleChoice::class.java)
            startActivity(intent)
        }
        btnhistory.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, TrueorFalse::class.java)
            startActivity(intent)
        }

    }
}