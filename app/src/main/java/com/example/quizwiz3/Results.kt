package com.example.quizwiz3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.SignInButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Results : AppCompatActivity() {
    private var mcQuestions: List<MultipleChoiceQuestion> = emptyList()
    private var tfQuestions: List<TrueOrFalseQuestion> = emptyList()

    private lateinit var rvCategories: RecyclerView
    private lateinit var QuestionsAdapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)

        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        val category = "MultipleChoice"
            //intent.getStringExtra("category") ?: "Default"
        val type = "MultipleChoice"
//            intent.getStringExtra("type") ?: "Default"//
        Toast.makeText(this@Results, category, Toast.LENGTH_SHORT).show()
        Toast.makeText(this@Results, type, Toast.LENGTH_SHORT).show()

        if(type == "MultipleChoice")
        {
            fetchQuestionsMultipleChoice()

        } else
        {
            fetchQuestionsTrueOrFalse(category)
        }
    }

    private fun displayQuestions(questions: List<String>) {

        QuestionsAdapter = QuestionsAdapter(questions) { selectedQuestion ->
            // Intent to navigate to ChatGPTResult activity
            val intent = Intent(this, ChatGPTResult::class.java)
            intent.putExtra("selectedQuestion", "how many eggs in a dozen?")
            startActivity(intent)
        }
        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = QuestionsAdapter
        Toast.makeText(this@Results, "working", Toast.LENGTH_SHORT).show()
    }


    private fun fetchQuestionsMultipleChoice() {
//        val apiService = RetrofitClient.instance.create(QuizApiService::class.java)
//        apiService.getMultipleChoiceQuestions(category).enqueue(object : Callback<List<MultipleChoiceQuestion>> {
//            override fun onResponse(call: Call<List<MultipleChoiceQuestion>>, response: Response<List<MultipleChoiceQuestion>>) {
//                if (response.isSuccessful) {
//                    mcQuestions = response.body() ?: emptyList()
//                    val sampleQuestions = listOf("Question 1", "Question 2", "Question 3", "Question 4", "Question 5")
//                    displayQuestions(sampleQuestions)
//                } else {
//                    Toast.makeText(this@Results, "Failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<MultipleChoiceQuestion>>, t: Throwable) {
//                Toast.makeText(this@Results, "Failed", Toast.LENGTH_SHORT).show()
//            }
 //       })
        Toast.makeText(this@Results, "questions", Toast.LENGTH_SHORT).show()
        val sampleQuestions = listOf("January", "February", "March")
        displayQuestions(sampleQuestions)
    }

    private fun fetchQuestionsTrueOrFalse(category: String) {
        val apiService = RetrofitClient.instance.create(QuizApiService::class.java)
        val call = apiService.getQuestions(category)

        call.enqueue(object : Callback<List<TrueOrFalseQuestion>> {
            override fun onResponse(
                call: Call<List<TrueOrFalseQuestion>>,
                response: Response<List<TrueOrFalseQuestion>>
            ) {
                if (response.isSuccessful) {
                    tfQuestions = response.body() ?: emptyList()

                } else {
                    Toast.makeText(this@Results, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TrueOrFalseQuestion>>, t: Throwable) {
                Toast.makeText(this@Results, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}