package com.example.quizapp_frontend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quizapp_frontend.model.CategoryEntity
import com.example.quizapp_frontend.model.GameStatistics
import com.example.quizapp_frontend.repository.CategoryRepository

class GameStatisticsViewModel(application: Application): AndroidViewModel(application) {
    val gameStatistics = GameStatistics()
    private val categoryRepository = CategoryRepository(application)


    fun correctAnswerSelected(categoryName:String){
        gameStatistics.totalNumberOfQuestions++
        gameStatistics.numberOfCorrectAnswers++
        categoryRepository.addCorrectAnswerToCategory(categoryName)
    }

    fun wrongAnswerSelected(categoryName:String){
        gameStatistics.totalNumberOfQuestions++
        gameStatistics.numberOfIncorrectAnswers++
        categoryRepository.addWrongAnswerToCategory(categoryName)
    }

    fun setPassedTimeInSeconds(passedTimeInSeconds:Int){
        gameStatistics.passedTimeInSeconds = passedTimeInSeconds
    }

    fun getCategories() : LiveData<List<CategoryEntity>> {
        return categoryRepository.getCategories()
    }

}