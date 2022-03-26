package com.example.a7asryan.favourite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a7asryan.R
import com.example.a7asryan.databinding.FragmentFavouriteScreenBinding
import com.example.a7asryan.favourite.viewmodel.FactoryFavouriteViewModel
import com.example.a7asryan.favourite.viewmodel.FavouriteResult
import com.example.a7asryan.favourite.viewmodel.FavouriteViewModel
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.model.Article
import com.example.a7asryan.remote.RetrofitHelper
import com.example.a7asryan.repository.Repository


class FavouriteScreen : Fragment() {
    private var _binding: FragmentFavouriteScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouriteViewModel by viewModels {
        FactoryFavouriteViewModel(Repository(ConcreteLocal(requireContext()), RetrofitHelper))
    }
    private lateinit var favouriteAdapter: FavouriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteScreenBinding.inflate(inflater, container, false)
        initFavRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavourites()
        viewModel.favourites.observe(viewLifecycleOwner) { favouriteResult ->
            when (favouriteResult) {
                FavouriteResult.Empty -> {
                    binding.animationView.setAnimation(R.raw.favorite)
                    binding.animationView.visibility = View.VISIBLE
                    binding.favRecycler.visibility = View.INVISIBLE
                }
                FavouriteResult.ErrorResult -> TODO()
                FavouriteResult.Loading -> {
                    binding.animationView.setAnimation(R.raw.loading)
                    binding.animationView.visibility = View.VISIBLE
                    binding.favRecycler.visibility = View.INVISIBLE
                }
                is FavouriteResult.Success -> {
                    binding.animationView.visibility = View.INVISIBLE
                    binding.favRecycler.visibility = View.VISIBLE
                    bindFavourites(favouriteResult.articles)
                }
            }

        }
    }

    private fun initFavRecycler() {
        favouriteAdapter = FavouriteAdapter(onDelete, onClick)
        binding.favRecycler.apply {
            adapter = favouriteAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.VERTICAL, false
            )
        }
    }

    private var onDelete: (Article) -> Unit = {
        viewModel.removeFavourite(it)
    }
    private var onClick: (String) -> Unit = { url ->
        val bundle = Bundle().apply {
            putString("url", url)
        }
        findNavController().navigate(R.id.action_favouriteScreen_to_displayDetails, bundle)
    }


    private fun bindFavourites(favArticles: List<Article>) {
        favouriteAdapter.setFavouriteList(favArticles)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}