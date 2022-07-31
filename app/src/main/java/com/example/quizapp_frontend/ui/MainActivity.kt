package com.example.quizapp_frontend.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp_frontend.R
import com.example.quizapp_frontend.navigation.NavigationMessage
import com.example.quizapp_frontend.repository.QuestionRepository
import com.example.quizapp_frontend.ui.fragments.MenuFragment
import com.example.quizapp_frontend.ui.fragments.QuestionFragment
import com.example.quizapp_frontend.ui.fragments.StatisticsFragment
import com.example.quizapp_frontend.viewmodel.EnvironmentalBrightnessViewModel
import com.example.quizapp_frontend.viewmodel.NavigationViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var questionRepository : QuestionRepository
    private lateinit var navigationViewModel : NavigationViewModel
    private lateinit var brightnessViewModel: EnvironmentalBrightnessViewModel
    private lateinit var sensorManager: SensorManager
    private lateinit var lightSensor: Sensor
    private lateinit var lightEventListener: SensorEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionRepository = QuestionRepository(application)
        navigationViewModel = ViewModelProvider(this)[NavigationViewModel::class.java]
        brightnessViewModel = ViewModelProvider(this)[EnvironmentalBrightnessViewModel::class.java]
        brightnessViewModel.updateLuminosityBright(false)
        setUpNavigationListeners()
        navigationViewModel.updateNavigationValue(NavigationMessage.MAIN_MENU)
        setupSensor()
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

    private fun showStatisticsFragment(){
        val statisticsFragment = StatisticsFragment()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragmentMain, statisticsFragment)
        fragmentManager.commit()
    }

    private fun setUpNavigationListeners(){
        navigationViewModel.navigationValue.observe(this){
            value ->
                if(value == NavigationMessage.SINGLE_PLAYER){
                    questionRepository.updateQuestions()
                    showSinglePlayerFragment()
                }else if(value == NavigationMessage.MAIN_MENU){
                    showMenuFragment()
                }else if(value == NavigationMessage.STATISTICS){
                    showStatisticsFragment()
                }
        }
    }

    private fun setupSensor(){
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        lightEventListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                val value = sensorEvent.values[0]
                Log.d("Luminosity :", "$value lx")
                if(value < 10){
                    brightnessViewModel.updateLuminosityBright(true)
                }else{
                    brightnessViewModel.updateLuminosityBright(false)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
        }
    }
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            lightEventListener,
            lightSensor,
            SensorManager.SENSOR_DELAY_FASTEST
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(lightEventListener)
    }

}