package com.example.a7asryan.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a7asryan.R
import com.example.a7asryan.databinding.FragmentHomeBinding
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.model.Article
import com.example.a7asryan.remote.RetrofitHelper
import com.example.a7asryan.repository.Repository

class HomeFragment : Fragment() {
    private lateinit var adapter : HomeNewsAdapter

    private var articaleList:List<Article> = emptyList()
    private  var searchList : ArrayList<Article> = arrayListOf()
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

        adapter= HomeNewsAdapter(articaleList,requireContext(),onClick,onCardClick)
        binding.homeRecycler.adapter =adapter

        viewModel.getData()
        viewModel.news.observe(viewLifecycleOwner){
            articaleList = it
            adapter.newsList = it
            adapter.notifyDataSetChanged()

        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText.toString())
                return false
            }

        })

        binding.search.setOnCloseListener(object :SearchView.OnCloseListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClose(): Boolean {
                adapter.newsList = articaleList
                adapter.notifyDataSetChanged()
                binding.search.focusable
                return false
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun filterList(searchText:String){
        searchList.clear()
        for (list in articaleList){
            list.title?.let {
                if(it.lowercase().contains(searchText.lowercase())){
                    searchList.add(list)
                }
            }

        }
        adapter.newsList = searchList
        adapter.notifyDataSetChanged()
    }

    var onClick:(Article) -> Unit= {
        viewModel.updateArticale(it)
    }
    var onCardClick:(String) -> Unit= {
        val bundle = Bundle().apply {
            putString("url",it)
        }
        findNavController().navigate(R.id.action_navigation_home_to_displayDetails,bundle)
    }
}