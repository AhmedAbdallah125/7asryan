package com.example.a7asryan.favourite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.a7asryan.R
import com.example.a7asryan.databinding.FragmentFavouriteScreenBinding
import com.example.a7asryan.favourite.viewmodel.FactoryFavouriteViewModel
import com.example.a7asryan.favourite.viewmodel.FavouriteViewModel
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.repository.Repository


class FavouriteScreen : Fragment() {
    private var _binding: FragmentFavouriteScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouriteViewModel by viewModels {
        FactoryFavouriteViewModel(Repository(ConcreteLocal(requireContext())))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}