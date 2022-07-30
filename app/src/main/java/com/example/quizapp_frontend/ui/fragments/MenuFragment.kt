package com.example.quizapp_frontend.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp_frontend.databinding.FragmentMenuBinding
import com.example.quizapp_frontend.navigation.NavigationMessage
import com.example.quizapp_frontend.viewmodel.NavigationViewModel

class MenuFragment : Fragment() {
    private lateinit var navigationViewModel: NavigationViewModel
    private lateinit var binding: FragmentMenuBinding

    override fun onStart(){
        super.onStart()
        navigationViewModel = ViewModelProvider(requireActivity())[NavigationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding.menuSinglePlayerButton.setOnClickListener{
            navigationViewModel.updateNavigationValue(NavigationMessage.SINGLE_PLAYER)
        }
        binding.menuStatisticButton.setOnClickListener{
            navigationViewModel.updateNavigationValue(NavigationMessage.STATISTICS)
        }
        return binding.root
    }

}