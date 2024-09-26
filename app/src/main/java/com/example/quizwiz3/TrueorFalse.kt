package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class TrueorFalse : AppCompatActivity() {

    private lateinit var Backbtn2: Button
    private lateinit var questionTXT2: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var resultTextView: TextView // Initialize resultTextView
    private  lateinit var dashboardbtn:Button

    private var questions: List<TrueOrFalseQuestion> = emptyList()
    private var currentQuestionIndex = 0
    private var score = 0 // Initialize score


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trueor_false)

        // Initialize views
        questionTXT2 = findViewById(R.id.questionTXT2)
        trueBtn = findViewById(R.id.truebtn)
        falseBtn = findViewById(R.id.falsebtn)
        nextBtn = findViewById(R.id.nextbtn)
        resultTextView = findViewById(R.id.resultTextView) // Initialize resultTextView

        // Get the category passed from Dashboard
        val category = intent.getStringExtra("category") ?: "Default"

        // Fetch questions for the category
        fetchQuestions(category)

        Backbtn2 = findViewById(R.id.Backbtn2)
        Backbtn2 = findViewById(R.id.Backbtn2)
        Backbtn2.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex-- // Decrease the index
                displayQuestion() // Display the previous question
            } else {
                Toast.makeText(this, "This is the first question.", Toast.LENGTH_SHORT).show()
            }
        }

        nextBtn.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            } else {
                displayScore() // Call displayScore when there are no more questions

                val intent = Intent(this, Results::class.java)
                intent.putExtra("category", category)
                intent.putExtra("type", "TrueOrFalse")
                startActivity(intent)
            }
        }

        trueBtn.setOnClickListener {
            checkAnswer(true)
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)
        }

        val dashboardbtn: Button = findViewById(R.id.dashboardbtn)

        // Handle Back button to go to PlayerSelection
        dashboardbtn.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }

    private fun fetchQuestions(category: String) {
        val apiService = RetrofitClient.instance.create(QuizApiService::class.java)
        val call = apiService.getQuestions(category)

        call.enqueue(object : Callback<List<TrueOrFalseQuestion>> {
            override fun onResponse(
                call: Call<List<TrueOrFalseQuestion>>,
                response: Response<List<TrueOrFalseQuestion>>
            ) {
                if (response.isSuccessful) {
                    questions = response.body() ?: emptyList()
                    if (questions.isNotEmpty()) {
                        displayQuestion()
                        Toast.makeText(this@TrueorFalse, "Questions fetched successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@TrueorFalse, "No questions found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("QuizWiz", "Failed to fetch questions: ${response.errorBody()?.string()}")
                    Toast.makeText(this@TrueorFalse, "Failed to fetch questions: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TrueOrFalseQuestion>>, t: Throwable) {
                Log.e("QuizWiz", "Error fetching questions", t)
                Toast.makeText(this@TrueorFalse, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayQuestion() {
        val question = questions[currentQuestionIndex]
        questionTXT2.text = question.questionText

        // Set answers based on the correct answer
        trueBtn.text = "TRUE"
        falseBtn.text = "FALSE"
    }

    private fun checkAnswer(selectedAnswer: Boolean) {
        val correctAnswer = questions[currentQuestionIndex].correctAnswer
        if (selectedAnswer == correctAnswer) {
            score++ // Increment score if the answer is correct
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayScore() {
        resultTextView.text = "Your score: $score/${questions.size}" // Display score
        nextBtn.isEnabled = false // Disable the next button
        trueBtn.isEnabled = false // Disable the true button
        falseBtn.isEnabled = false // Disable the false button
    }
}
