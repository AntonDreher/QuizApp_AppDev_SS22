package com.example.quizapp_frontend.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp_frontend.R
import com.example.quizapp_frontend.databinding.FragmentQuestionBinding
import com.example.quizapp_frontend.viewmodel.QuestionViewModel

class QuestionFragment : Fragment() {
    private lateinit var questionViewModel : QuestionViewModel
    lateinit var binding: FragmentQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        questionViewModel.updateCurrentQuestion()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        binding.lifecycleOwner = this
        binding.questionViewModel = questionViewModel
        return binding.root
    }


}