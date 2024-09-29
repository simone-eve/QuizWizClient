package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.quizwiz3.R.id

class PlayerSelection : AppCompatActivity() {

    private lateinit var singlebtn: ImageButton
    private lateinit var multibtn: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Find buttons by their IDs
        singlebtn = findViewById(id.singlebtn)
        multibtn = findViewById(id.multibtn)

        // Set OnClickListeners for the buttons
        singlebtn.setOnClickListener {
            // Open the Dashboard Activity
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        multibtn.setOnClickListener {
            // Open the Dashboard Activity
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }

    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Handle menu item selections
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                // Open Profile Activity
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
                return true
            }
            R.id.dashboard -> {
                // Open Dashboard Activity
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                return true
            }
            R.id.settings -> {
                // Open Settings Activity
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
                return true
            }
            R.id.helpsupport -> {
                // Open Help Activity
                val intent = Intent(this, HelpSupport::class.java)
                startActivity(intent)
                return true
            }
            R.id.about -> {
                // Open About Activity
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout -> {
                // Handle logout functionality
                Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
