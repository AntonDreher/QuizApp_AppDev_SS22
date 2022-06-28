package com.example.quizapp_frontend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quizapp_frontend.navigation.NavigationMessage

class NavigationViewModel(application: Application) : AndroidViewModel(application){
    var navigationValue = MutableLiveData<NavigationMessage>()

    fun updateNavigationValue(newNavigationValue: NavigationMessage){
        navigationValue.value = newNavigationValue
    }
}