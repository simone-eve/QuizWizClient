package com.example.quizwiz3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizApiService {

    @GET("api/TrueOrFalseQuiz/get/{category}")
    fun getQuestions(@Path("category") category: String): Call<List<TrueOrFalseQuestion>>

    @GET("api/Quiz/get/{category}")
    fun getMultipleChoiceQuestions(@Path("category") category: String): Call<List<MultipleChoiceQuestion>>
}
