package com.example.quizapp_frontend.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapp_frontend.model.QuestionEntity
import com.example.quizapp_frontend.repository.QuestionRepository

class QuestionViewModel(application: Application): AndroidViewModel(application) {
    private val questionRepository:QuestionRepository = QuestionRepository(application)
    private var currentQuestion = MutableLiveData<QuestionEntity>()
    val answerSelected = MutableLiveData(false)

    fun updateCurrentQuestion(){
        questionRepository.getRandomQuestion().observeForever{
            question ->
            run {
                currentQuestion.value = question
                answerSelected.value = false
            }
        }
    }

    fun getCurrentQuestion() : MutableLiveData<QuestionEntity>{
        return currentQuestion
    }

    fun answerSelected(){
        answerSelected.value = true
    }
}