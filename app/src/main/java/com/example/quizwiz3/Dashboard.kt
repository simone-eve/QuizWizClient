package com.example.quizwiz3
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem

import android.widget.Button
import android.widget.ImageButton
class Dashboard : AppCompatActivity() {

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

        // Handle Back button to go to PlayerSelection
        Backbtn2.setOnClickListener {
            val intent = Intent(this, PlayerSelection::class.java)
            startActivity(intent)
        }

        animalbtn.setOnClickListener {
            Log.d("Dashboard", "Animal button clicked, launching MultiChoice")
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Animals")
            startActivity(intent)
        }

        btnfood.setOnClickListener {
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Food")
            startActivity(intent)
        }

        btnmusic.setOnClickListener {
            val intent = Intent(this, MultiChoice::class.java)
            intent.putExtra("category", "Music")
            startActivity(intent)
        }

        btndisney.setOnClickListener {
            val intent = Intent(this, TrueorFalse::class.java)
            intent.putExtra("category", "Disney")
            startActivity(intent)
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

    }
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.profile -> {
                    startActivity(Intent(this, Profile::class.java))
                    return true
                }

                R.id.dashboard -> {
                    startActivity(Intent(this, Dashboard::class.java))
                    return true
                }

                R.id.settings -> {
                    startActivity(Intent(this, Settings::class.java))
                    return true
                }

                R.id.helpsupport -> {
                    startActivity(Intent(this, HelpSupport::class.java))
                    return true
                }

                R.id.about -> {
                    startActivity(Intent(this, About::class.java))
                    return true
                }

                R.id.logout -> {
                    finish()
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }
    }


