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
    private var alreadyAskedQuestionsId = ArrayList<String>()
    val answerSelected = MutableLiveData(false)

    fun updateCurrentQuestion(){
        questionRepository.getRandomQuestion(alreadyAskedQuestionsId).observeForever{
            question ->
            run {
                currentQuestion.value = question
                if(question != null) {
                    alreadyAskedQuestionsId.add(question.id)
                }
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