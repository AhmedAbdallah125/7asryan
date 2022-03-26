package com.example.a7asryan.displaydetails.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.a7asryan.R
import com.example.a7asryan.databinding.DisplayDetailsFragmentBinding
import com.example.a7asryan.displaydetails.viewmodel.DisplayDetailsViewModel
import com.example.a7asryan.displaydetails.viewmodel.DisplayDetailsViewModelFactory
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.repository.Repository

class DisplayDetails : Fragment() {

    private val viewModel: DisplayDetailsViewModel by viewModels {
        DisplayDetailsViewModelFactory(Repository(ConcreteLocal(requireContext())))
    }
    private lateinit var _binding: DisplayDetailsFragmentBinding
    private val binding = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DisplayDetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}