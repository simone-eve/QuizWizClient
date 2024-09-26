package com.example.quizwiz3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MultiChoice : AppCompatActivity() {

    private lateinit var QuestionTXT: TextView
    private lateinit var answerButton1: Button
    private lateinit var answerButton2: Button
    private lateinit var answerButton3: Button
    private lateinit var nextbtn: Button
    private lateinit var backbtn: Button
    private lateinit var resultTextView:TextView
    private  lateinit var dashboardbtn:Button

    private var questions: List<MultipleChoiceQuestion> = emptyList()
    private var currentQuestionIndex = 0
    private var selectedAnswer: String? = null
    private var score = 0 // Initialize the score

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_choice)

        QuestionTXT = findViewById(R.id.QuestionTXT)
        answerButton1 = findViewById(R.id.answer1)
        answerButton2 = findViewById(R.id.answer2)
        answerButton3 = findViewById(R.id.answer3)
        nextbtn = findViewById(R.id.nextbtn)
        backbtn = findViewById(R.id.backbtn)
        resultTextView = findViewById(R.id.resultTextView)

        val category = intent.getStringExtra("category") ?: "Default"
        fetchQuestions(category)

        // Set click listeners for answer buttons
        answerButton1.setOnClickListener { selectAnswer(answerButton1.text.toString()) }
        answerButton2.setOnClickListener { selectAnswer(answerButton2.text.toString()) }
        answerButton3.setOnClickListener { selectAnswer(answerButton3.text.toString()) }

        val dashboardbtn: Button = findViewById(R.id.dashboardbtn)

        // Handle Back button to go to PlayerSelection
        dashboardbtn.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        // Set click listener for next button
        nextbtn.setOnClickListener { showNextQuestion(category) }

        // Set click listener for back button
        backbtn.setOnClickListener { showPreviousQuestion() }
    }

    private fun showPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex-- // Move to the previous question
            displayCurrentQuestion() // Update the UI to show the previous question
            selectedAnswer = null // Reset selected answer for the previous question
        } else {
            Toast.makeText(this, "This is the first question", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectAnswer(answer: String) {
        selectedAnswer = answer
    }

    private fun showNextQuestion(category: String) {
        if (selectedAnswer != null) {
            // Check if the answer is correct
            val currentQuestion = questions[currentQuestionIndex]
            if (selectedAnswer == currentQuestion.answer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                score++ // Increment score for correct answer
            } else {
                Toast.makeText(this, "Incorrect! The correct answer is: ${currentQuestion.answer}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please select an answer first", Toast.LENGTH_SHORT).show()
            return
        }

        // Move to the next question
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            displayCurrentQuestion()
            selectedAnswer = null // Reset selected answer for the next question
        } else {
            displayFinalScore() // Show score after the last question

            val intent = Intent(this, Results::class.java)
            intent.putExtra("category", category)
            intent.putExtra("type", "MultipleChoice")
            startActivity(intent)
        }
    }

    private fun fetchQuestions(category: String) {
        val apiService = RetrofitClient.instance.create(QuizApiService::class.java)
        apiService.getMultipleChoiceQuestions(category).enqueue(object : Callback<List<MultipleChoiceQuestion>> {
            override fun onResponse(call: Call<List<MultipleChoiceQuestion>>, response: Response<List<MultipleChoiceQuestion>>) {
                if (response.isSuccessful) {
                    questions = response.body() ?: emptyList()
                    displayCurrentQuestion()
                } else {
                    // Handle the error
                    QuestionTXT.text = "Failed to load questions"
                }
            }

            override fun onFailure(call: Call<List<MultipleChoiceQuestion>>, t: Throwable) {
                // Handle the failure
                QuestionTXT.text = "Error: ${t.message}"
            }
        })
    }

    private fun displayCurrentQuestion() {
        if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            QuestionTXT.text = currentQuestion.questionText

            val optionsList = currentQuestion.options.values.toList()  // Convert to List<String>

            answerButton1.text = optionsList.getOrNull(0) ?: ""
            answerButton2.text = optionsList.getOrNull(1) ?: ""
            answerButton3.text = optionsList.getOrNull(2) ?: ""
        } else {
            QuestionTXT.text = "No more questions available"
        }
    }

    private fun displayFinalScore() {
        // Display score in resultTextView
        resultTextView.text = "Quiz Finished! Your score: $score/${questions.size}"

        // Optionally, you can also show a Toast
        // Toast.makeText(this, "Quiz Finished! Your score: $score/${questions.size}", Toast.LENGTH_LONG).show()

        // Disable the next button if no more questions
        nextbtn.isEnabled = false
    }

}
