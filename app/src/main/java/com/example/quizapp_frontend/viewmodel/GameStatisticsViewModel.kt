package com.example.quizapp_frontend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quizapp_frontend.model.GameStatistics

class GameStatisticsViewModel(application: Application): AndroidViewModel(application) {
    val gameStatistics = GameStatistics()

    fun correctAnswerSelected(){
        gameStatistics.totalNumberOfQuestions++
        gameStatistics.numberOfCorrectAnswers++
    }

    fun wrongAnswerSelected(){
        gameStatistics.totalNumberOfQuestions++
        gameStatistics.numberOfIncorrectAnswers++
    }

    fun setPassedTimeInSeconds(passedTimeInSeconds:Int){
        gameStatistics.passedTimeInSeconds = passedTimeInSeconds
    }
}