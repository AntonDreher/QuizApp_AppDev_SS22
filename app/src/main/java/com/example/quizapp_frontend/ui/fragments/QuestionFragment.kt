package com.example.quizapp_frontend.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp_frontend.R
import com.example.quizapp_frontend.databinding.FragmentQuestionBinding
import com.example.quizapp_frontend.model.QuestionEntity
import com.example.quizapp_frontend.ui.fragments.answer_fragments.CorrectAnswerFragment
import com.example.quizapp_frontend.ui.fragments.answer_fragments.WrongAnswerFragment
import com.example.quizapp_frontend.viewmodel.QuestionViewModel

class QuestionFragment : Fragment() {
    private lateinit var questionViewModel : QuestionViewModel
    private var answerContainer = IntArray(4)
    private var incorrectAnswers = Array(3){""}
    lateinit var binding: FragmentQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
        fillContainerArray()
        fillQuestion()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        binding.lifecycleOwner = this
        binding.questionViewModel = questionViewModel
        return binding.root
    }

    private fun initializeViewModel(){
        questionViewModel = ViewModelProvider(requireActivity())[QuestionViewModel::class.java]
        questionViewModel.updateCurrentQuestion()
    }

    private fun fillContainerArray(){
        answerContainer[0] = R.id.answerPossibilityContainer1
        answerContainer[1] = R.id.answerPossibilityContainer2
        answerContainer[2] = R.id.answerPossibilityContainer3
        answerContainer[3] = R.id.answerPossibilityContainer4
    }

    private fun fillQuestion(){
        questionViewModel.getCurrentQuestion().observeForever()
        { question ->
            if(question != null) {
                fillIncorrectAnswerArray(question)
                val correctAnswerPosition = (0..3).shuffled().last()
                fillCorrectAnswer(question.correctAnswer, correctAnswerPosition)
                fillIncorrectAnswers(correctAnswerPosition)
            }else{
                showStatisticDialog()
            }
        }
    }

    private fun fillCorrectAnswer(correctAnswer: String, correctAnswerPosition: Int){
        val fragmentManager = childFragmentManager.beginTransaction()
        val wrongAnswerFragment = CorrectAnswerFragment()
        val bundle = Bundle()
        bundle.putString("answer", correctAnswer)
        wrongAnswerFragment.arguments = bundle
        fragmentManager.replace(answerContainer[correctAnswerPosition], wrongAnswerFragment)
        fragmentManager.commit()
    }

    private fun fillIncorrectAnswers(correctAnswerPosition: Int){
        var currentPosition = 0
        for(i in 0..3){
            if(i != correctAnswerPosition){
                fillWrongAnswerFragment(i, currentPosition)
                currentPosition++
            }
        }
    }

    private fun fillWrongAnswerFragment(containerPosition:Int, incorrectAnswerPosition: Int){
        val fragmentManager = childFragmentManager.beginTransaction()
        val wrongAnswerFragment = WrongAnswerFragment()
        val bundle = Bundle()
        bundle.putString("answer", incorrectAnswers[incorrectAnswerPosition])
        wrongAnswerFragment.arguments = bundle
        fragmentManager.replace(answerContainer[containerPosition], wrongAnswerFragment)
        fragmentManager.commit()
    }

    private fun fillIncorrectAnswerArray(question:QuestionEntity){
        incorrectAnswers[0] = question.incorrectAnswer1
        incorrectAnswers[1] = question.incorrectAnswer2
        incorrectAnswers[2] = question.incorrectAnswer3
    }

    private fun  startTimer(){
        val fragment = childFragmentManager.beginTransaction()
        val progressFragment = ProgressFragment()
        fragment.replace(R.id.fragmentProgressBarContainer, progressFragment)
    }

    private fun showStatisticDialog(){
       GameFinishedDialogFragment().show(childFragmentManager, GameFinishedDialogFragment.TAG)
    }
}