package com.example.a7asryan.displaydetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.a7asryan.R
import com.example.a7asryan.databinding.DisplayDetailsFragmentBinding
import com.example.a7asryan.displaydetails.viewmodel.DisplayDetailsViewModel
import com.example.a7asryan.displaydetails.viewmodel.DisplayDetailsViewModelFactory
import com.example.a7asryan.local.ConcreteLocal
import com.example.a7asryan.model.Article
import com.example.a7asryan.remote.RetrofitHelper
import com.example.a7asryan.repository.Repository

class DisplayDetails : Fragment() {

    private val viewModel: DisplayDetailsViewModel by viewModels {
        DisplayDetailsViewModelFactory(Repository(ConcreteLocal(requireContext()), RetrofitHelper))
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
        requireArguments().getString("url")?.let { url ->
            viewModel.getArticle(url)
        }
        viewModel.articleResponse.observe(viewLifecycleOwner) { article ->
            bindData(article)
            binding.btnFavorite.setOnClickListener {
                article.isFavourite = !article.isFavourite
                setFavoriteIcon(article.isFavourite)
                viewModel.updateFavoriteArticle(article)
            }
            binding.btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_displayDetails_to_navigation_home)
            }
        }
    }

    private fun bindData(article: Article) {
        setFavoriteIcon(article.isFavourite)
        binding.textSourceName.text = article.sourceName
        binding.textTitle.text = article.title
        Glide.with(requireContext()).load(article.urlToImage).into(binding.newsImage)
        binding.textPublishedAt.text = article.publishedAt
        binding.textDescription.text = article.description
        binding.textLink.text = article.url
    }

    private fun setFavoriteIcon(favourite: Boolean) {
        if (favourite) {
            binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

}