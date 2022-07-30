package com.example.quizapp_frontend.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_frontend.R
import com.example.quizapp_frontend.model.CategoryEntity
import com.example.quizapp_frontend.navigation.NavigationMessage
import com.example.quizapp_frontend.ui.adapter.RecyclerViewGameStatisticsAdapter
import com.example.quizapp_frontend.viewmodel.GameStatisticsViewModel
import com.example.quizapp_frontend.viewmodel.NavigationViewModel


class StatisticsFragment : Fragment() {
    private lateinit var gameStatisticsViewModel: GameStatisticsViewModel
    private lateinit var categories : ArrayList<CategoryEntity>
    private lateinit var rvCategories : RecyclerView
    private lateinit var navigationViewModel: NavigationViewModel

    override fun onStart() {
        super.onStart()
        gameStatisticsViewModel = ViewModelProvider(requireActivity())[GameStatisticsViewModel::class.java]
        navigationViewModel = ViewModelProvider(requireActivity())[NavigationViewModel::class.java]
        gameStatisticsViewModel.getCategories().observe(this){
            value ->
                categories = value as ArrayList<CategoryEntity>
                categories.sortByDescending{
                    it -> it.correctAnswers.toDouble()/(it.wrongAnswers.toDouble()+it.correctAnswers.toDouble())
                }
                rvCategories = getView()?.findViewById<View>(R.id.gameStatisticRecyclerView) as RecyclerView
                rvCategories.setHasFixedSize(true)
                rvCategories.layoutManager = LinearLayoutManager(view?.context)
                val adapter = RecyclerViewGameStatisticsAdapter(categories)
                rvCategories.adapter = adapter
        }

        view?.findViewById<Button>(R.id.returnToMainMenuFromStatistics)?.setOnClickListener{
            navigationViewModel.updateNavigationValue(NavigationMessage.MAIN_MENU)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

}