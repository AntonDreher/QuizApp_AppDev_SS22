package com.example.quizapp_frontend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quizapp_frontend.model.QuestionEntity
import com.example.quizapp_frontend.repository.QuestionRepository

//TODO was abstract
class QuestionViewModel(application: Application): AndroidViewModel(application) {
    private val questionRepository:QuestionRepository = QuestionRepository(application)

    fun getRandomQuestion() : LiveData<QuestionEntity>{
        return questionRepository.getRandomQuestion()
    }
}