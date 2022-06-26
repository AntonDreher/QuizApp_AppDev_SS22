package com.example.quizapp_frontend

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp_frontend.api.QuestionResponse
import com.example.quizapp_frontend.api.QuizApi
import com.example.quizapp_frontend.model.QuestionEntity
import com.example.quizapp_frontend.repository.QuestionRepository
import com.example.quizapp_frontend.viewmodel.QuestionViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var questionViewModel : QuestionViewModel
    private lateinit var currentQuestion : QuestionEntity
    private lateinit var questionRepository : QuestionRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionRepository = QuestionRepository(application)
        makeRequest()

        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        questionViewModel.getRandomQuestion().observe(this
        ) { question ->
            Log.d("main", "onChanged: $question")
        }
    }

    private fun makeRequest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://the-trivia-api.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: QuizApi = retrofit.create(QuizApi::class.java)
        val call: Call<List<QuestionResponse>> = api.getQuestions()
        call.enqueue(object : Callback<List<QuestionResponse>> {
            override fun onResponse(call: Call<List<QuestionResponse>>, response: Response<List<QuestionResponse>>) {
                if (response.isSuccessful) {
                    response.body()?.let { questionRepository.insert(it) }
                }
            }

            override fun onFailure(call: Call<List<QuestionResponse>>, t: Throwable) {
                Log.d("main", "onFailure: " + t.message)
            }
        })
    }
}