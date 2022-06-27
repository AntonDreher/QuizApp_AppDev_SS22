package com.example.quizapp_frontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizapp_frontend.databinding.FragmentCorrectAnswerBinding
import com.example.quizapp_frontend.databinding.FragmentWrongAnswerBinding

class CorrectAnswerFragment : Fragment() {
    private lateinit var binding: FragmentCorrectAnswerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val answer = arguments?.getString("answer")
        binding = FragmentCorrectAnswerBinding.inflate(inflater, container, false)
        binding.correctAnswerTextViewId.setText(answer)
        return binding.root
    }
}