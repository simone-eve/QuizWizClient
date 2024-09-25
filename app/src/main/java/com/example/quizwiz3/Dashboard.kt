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
    // private lateinit var animalbtn: ImageButton
    //  private lateinit var btnfood: ImageButton
    //  private lateinit var btnmusic: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val Backbtn2: Button = findViewById(R.id.Backbtn2)
        val animalbtn: ImageButton = findViewById(R.id.animalbtn)
        val btnfood: ImageButton = findViewById(R.id.btnfood)
        val btndisney: ImageButton = findViewById(R.id.btndisney) // Disney button
        val btnmusic: ImageButton = findViewById(R.id.btnmusic)
        val btntvshows: ImageButton = findViewById(R.id.btntvshows)
        val btnhistory: ImageButton = findViewById(R.id.btnhistory)

        // Handle Back button to go to PlayerSelection
        Backbtn2.setOnClickListener {
            val intent = Intent(this, PlayerSelection::class.java)
            startActivity(intent)
        }


        btnfood.setOnClickListener {
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Food") // Pass t
            startActivity(intent)
        }

        btnmusic.setOnClickListener {
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Music") // P
            startActivity(intent)
        }

        btndisney.setOnClickListener {
            val intent = Intent(this, TrueorFalse::class.java)
            intent.putExtra("category", "Disney") // Pass the category as an extra
            startActivity(intent) // Start TrueorFalse activity
        }


        btntvshows.setOnClickListener {
            val intent = Intent(this, TrueorFalse::class.java)
            intent.putExtra("category", "TV Shows")
            startActivity(intent)
        }

        btnhistory.setOnClickListener {
            val intent = Intent(this, TrueorFalse::class.java)
            intent.putExtra("category", "History")
            startActivity(intent)
        }

        // Handle category buttons
        animalbtn.setOnClickListener {
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Animals") // Pass the category as an extra
            startActivity(intent)
        }

        btnfood.setOnClickListener {
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Food") // Pass the category as an extra
            startActivity(intent)
        }

        btnmusic.setOnClickListener {
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Music") // Pass the category as an extra
            startActivity(intent)
        }


    }
}
