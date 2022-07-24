package com.example.bmo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.bmo.R
import com.example.bmo.databinding.FragmentFavoriteNewsBinding
import com.example.bmo.viewmodel.NewsViewModel

class FavoriteNewsFragment : Fragment() {

    val TITLE = "Favorites"

    private var _binding : FragmentFavoriteNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var view_model: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = requireContext()
        val activity = requireActivity()

        view_model = activity.let { ViewModelProvider(it)[NewsViewModel::class.java] }

        _binding = FragmentFavoriteNewsBinding.inflate(inflater, container, false)
        binding.apply {


            return root
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}