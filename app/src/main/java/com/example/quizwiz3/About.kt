package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.androidgamesdk.gametextinput.Settings

class About : AppCompatActivity() {


    private lateinit var btnBackSettings: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)

        btnBackSettings = findViewById(R.id.btnBackSettings)


        // Back to Settings Menu Button
        btnBackSettings = findViewById(R.id.btnBackSettings)
        btnBackSettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)

            startActivity(intent)
        }
        val intent = Intent(this, About::class.java)

        startActivity(intent)
    }

}




