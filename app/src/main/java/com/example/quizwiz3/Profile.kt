package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }
}
