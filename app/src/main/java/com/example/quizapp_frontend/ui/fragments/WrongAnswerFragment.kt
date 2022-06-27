package com.example.quizapp_frontend.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizapp_frontend.databinding.FragmentWrongAnswerBinding

class WrongAnswerFragment : Fragment() {
    private lateinit var binding: FragmentWrongAnswerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val answer = arguments?.getString("answer")
        binding = FragmentWrongAnswerBinding.inflate(inflater, container, false)
        binding.wrongAnswerTextViewId.setText(answer)
        return binding.root
    }

}