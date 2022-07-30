package com.example.quizapp_frontend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class EnvironmentalBrightnessViewModel (application: Application) : AndroidViewModel(application){
    var cheatActive = MutableLiveData<Boolean>()

    fun updateLuminosityBright(activateCheat:Boolean){
        cheatActive.value = activateCheat
    }
}