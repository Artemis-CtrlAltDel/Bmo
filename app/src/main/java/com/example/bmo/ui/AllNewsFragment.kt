package com.example.bmo.ui

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmo.adapters.AllNewsAdapter
import com.example.bmo.adapters.OnItemClick
import com.example.bmo.databinding.FragmentAllNewsBinding
import com.example.bmo.others.API_KEY
import com.example.bmo.others.LocationService
import com.example.bmo.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

class AllNewsFragment : Fragment() {

    val TITLE = "World news"

    private var _binding : FragmentAllNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var location_service: LocationService
    private lateinit var view_model: NewsViewModel

    private lateinit var adapter: AllNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val context = requireContext()
        val activity = requireActivity()

        view_model = activity.let { ViewModelProvider(it)[NewsViewModel::class.java] }

        _binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        binding.apply {

            view_model.all_news(API_KEY, q = "a", page_size = 20)

            location_service = LocationService(context)
            location_service.location_update {
                val last_location = it.lastLocation
                val latitude = last_location.latitude
                val longitude = last_location.longitude

                val current_location = Geocoder(context)
                    .getFromLocation(latitude, longitude, 1)[0]

                current_location.apply {
                    view_model.top_news(API_KEY, country = current_location.countryCode.lowercase(), page_size = 10)
                }
            }

            adapter =
                AllNewsAdapter(arrayListOf(), object: OnItemClick {
                    override fun on_favorite_click(position: Int) {
                    }

                    override fun on_bookmark_click(position: Int) {
                    }

                })

            view_model.news_list.observe(activity)
            {
                if (it.isNotEmpty()) {
                    adapter.set_items(it)
                }
            }

            latestNewsRecycler.adapter = adapter
            latestNewsRecycler.setHasFixedSize(true)
            latestNewsRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            return root
        }

    }

    override fun onPause() {
        super.onPause()
        location_service.stop_location_update()
    }

    override fun onResume() {
        super.onResume()
        location_service.start_location_update()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}