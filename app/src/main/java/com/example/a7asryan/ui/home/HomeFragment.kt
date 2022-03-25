package com.example.a7asryan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a7asryan.databinding.FragmentHomeBinding
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.remote.RetrofitHelper
import com.example.a7asryan.repository.Repository

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
     val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(Repository(ConcreteLocal(requireContext()),RetrofitHelper))
    }


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.homeRecycler.hasFixedSize()

        viewModel.getData()
        viewModel.news.observe(viewLifecycleOwner){
            var adapter : HomeNewsAdapter = HomeNewsAdapter(it,requireContext())
            binding.homeRecycler.adapter =adapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}