package com.example.quizapp_frontend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quizapp_frontend.model.QuestionEntity
import com.example.quizapp_frontend.repository.QuestionRepository

class QuestionViewModel(application: Application): AndroidViewModel(application) {
    private val questionRepository:QuestionRepository = QuestionRepository(application)
    private var currentQuestion = MutableLiveData<QuestionEntity>()
    var currentCategory : String = ""
    private var alreadyAskedQuestionsId = ArrayList<String>()
    val answerSelected = MutableLiveData(false)

    fun updateCurrentQuestion(){
        questionRepository.getRandomQuestion(alreadyAskedQuestionsId).observeForever{
            question ->
            run {
                currentQuestion.value = question
                if(question != null) {
                    alreadyAskedQuestionsId.add(question.id)
                    currentCategory = question.category
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