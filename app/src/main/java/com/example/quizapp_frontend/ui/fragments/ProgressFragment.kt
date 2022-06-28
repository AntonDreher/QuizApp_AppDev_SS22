package com.example.quizapp_frontend.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizapp_frontend.databinding.FragmentProgressBinding


class ProgressFragment : Fragment() {
    private lateinit var binding: FragmentProgressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        displayProgress()
        return binding.root
    }

    private fun displayProgress(){
     /*   val mCountDownTimer: CountDownTimer
        var i = 0

        binding.progressBar.progress = i
        mCountDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                i++
                binding.progressBar.progress = i as Int * 100 / (5000 / 1000)
            }

            override fun onFinish() {
                //Do what you want
                i++
                binding.progressBar.progress = 100
            }
        }
        mCountDownTimer.start()
    */}
}