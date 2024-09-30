package com.example.quizwiz3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Profile : AppCompatActivity() {
    private lateinit var iconRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
       // iconRecyclerView = findViewById(R.id.iconRecyclerView)
        iconRecyclerView.layoutManager = GridLayoutManager(this, 3)

        // List of drawable resource IDs for the icons
        val icons = listOf(R.drawable.icon1, R.drawable.icon2)

      //  val iconAdapter = IconAdapter(icons, this)
      //  iconRecyclerView.adapter = iconAdapter
    }

}
   // override fun onIconClick(icon: Int) {
        // Handle icon selection
      //  updateProfileIcon(icon)
  //  }

//    private fun updateProfileIcon(selectedIcon: Int) {
//        val user = FirebaseAuth.getInstance().currentUser
//        val profileUpdates = UserProfileChangeRequest.Builder()
//            .setPhotoUri(Uri.parse("android.resource://com.example.quizwiz3/$selectedIcon"))
//            .build()
//
//        user?.updateProfile(profileUpdates)
//            ?.addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "Profile icon updated!", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
