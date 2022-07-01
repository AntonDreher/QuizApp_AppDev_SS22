package com.example.quizapp_frontend.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp_frontend.R
import com.example.quizapp_frontend.navigation.NavigationMessage
import com.example.quizapp_frontend.repository.QuestionRepository
import com.example.quizapp_frontend.ui.fragments.MenuFragment
import com.example.quizapp_frontend.ui.fragments.QuestionFragment
import com.example.quizapp_frontend.viewmodel.NavigationViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var questionRepository : QuestionRepository
    private lateinit var navigationViewModel : NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionRepository = QuestionRepository(application)
        navigationViewModel = ViewModelProvider(this)[NavigationViewModel::class.java]
        setUpNavigationListeners()
        questionRepository.updateQuestions()
        navigationViewModel.updateNavigationValue(NavigationMessage.MAIN_MENU)
    }

    private fun showMenuFragment(){
        val menuFragment = MenuFragment()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragmentMain, menuFragment)
        fragmentManager.commit()
    }
    private fun showSinglePlayerFragment(){
        val questionFragment = QuestionFragment()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragmentMain, questionFragment)
        fragmentManager.commit()
    }

    private fun setUpNavigationListeners(){
        navigationViewModel.navigationValue.observe(this){
            value ->
                if(value == NavigationMessage.SINGLE_PLAYER){
                    showSinglePlayerFragment()
                }else if(value == NavigationMessage.MAIN_MENU){
                    showMenuFragment()
                }
        }
    }
}