package com.example.quizwiz3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class TrueorFalse : AppCompatActivity() {

    private lateinit var Backbtn2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trueor_false)

        val Backbtn2: Button = findViewById(R.id.Backbtn2)

        Backbtn2.setOnClickListener {
            // Create an Intent to start the PlayerSelection activity
            val intent = Intent(this, MultipleChoice::class.java)
            startActivity(intent)
        }
    }
}