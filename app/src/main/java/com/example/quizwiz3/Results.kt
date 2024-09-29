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
    private var cachedQuestionsMC: List<MultipleChoiceQuestion> = emptyList()
    private var cachedQuestionsTF: List<TrueOrFalseQuestion> = emptyList()

    private lateinit var rvCategories: RecyclerView
    private lateinit var QuestionsAdapter: QuestionsAdapter
    private lateinit var QuestionsAdapterTrueOrFalse: QuestionsAdapterTrueOrFalse
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)

        cachedQuestionsMC = QuestionCache.cachedQuestionsMC
        cachedQuestionsTF = QuestionCache.cachedQuestionsTF

        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        resultTextView = findViewById(R.id.resultTextView)
        val category = intent.getStringExtra("category") ?: "Default"
        val type = intent.getStringExtra("type") ?: "Default"
        val score = intent.getStringExtra("score") ?: "Default"
        val totalQuestions = intent.getStringExtra("totalQuestions") ?: "Default"

        resultTextView.text = "Quiz Finished! Your score: $score/${totalQuestions}"
        if(type == "MultipleChoice")
        {
            fetchQuestionsMultipleChoice(cachedQuestionsMC)

        } else
        {
            fetchQuestionsTrueOrFalse(cachedQuestionsTF)
            Toast.makeText(this@Results, "fetching tf questions", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayQuestionsMC(questions: List<MultipleChoiceQuestion>) {
        QuestionsAdapter = QuestionsAdapter(questions) { selectedQuestion ->

            // Convert the options from a map to a list
            val optionsList = selectedQuestion.options.values.toList()
            // Pass both question and options to the next activity
            val intent = Intent(this, ChatGPTResult::class.java)
            intent.putExtra("selectedQuestion", selectedQuestion.questionText)
            intent.putStringArrayListExtra("optionsList", ArrayList(optionsList)) // Pass the options list
            startActivity(intent)
        }

        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = QuestionsAdapter
    }

    private fun displayQuestionsTF(questions: List<TrueOrFalseQuestion>) {
        QuestionsAdapterTrueOrFalse = QuestionsAdapterTrueOrFalse(questions) { selectedQuestion ->

            // Pass both question and options to the next activity
            val intent = Intent(this, ChatGPTResult::class.java)
            intent.putExtra("selectedQuestion", selectedQuestion.questionText)
            startActivity(intent)
        }

        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = QuestionsAdapterTrueOrFalse
    }



    private fun fetchQuestionsMultipleChoice(cachedQuestionMC2: List<MultipleChoiceQuestion>) {
     displayQuestionsMC(cachedQuestionMC2)
    }

    private fun fetchQuestionsTrueOrFalse(cachedQuestionsTF2: List<TrueOrFalseQuestion>) {
        displayQuestionsTF(cachedQuestionsTF2)
    }


}