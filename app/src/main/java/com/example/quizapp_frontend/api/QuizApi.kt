package com.example.quizapp_frontend.api

import retrofit2.Call
import retrofit2.http.GET

interface QuizApi {
    @GET("questions/")
    fun getQuestions() : Call<List<QuestionResponse>>
}