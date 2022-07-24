package com.example.bmo.ui

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.bmo.R
import com.example.bmo.databinding.FragmentAllNewsBinding
import com.example.bmo.databinding.FragmentTopNewsBinding
import com.example.bmo.others.API_KEY
import com.example.bmo.others.LocationService
import com.example.bmo.viewmodel.NewsViewModel

class TopNewsFragment : Fragment() {

    val TITLE = "Local news"

    private var _binding : FragmentTopNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var location_service: LocationService
    private lateinit var top_news_view_model: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = requireContext()
        val activity = requireActivity()

        location_service = LocationService(activity)
        top_news_view_model = activity.let { ViewModelProvider(it)[NewsViewModel::class.java] }

        _binding = FragmentTopNewsBinding.inflate(inflater, container, false)
        binding.apply {

            location_service.location_update {
                val last_location = it.lastLocation
                val latitude = last_location.latitude
                val longitude = last_location.longitude

                val current_location = Geocoder(context)
                    .getFromLocation(latitude, longitude, 1)[0]

                current_location.apply {
                    someText.text = current_location.locality
                    top_news_view_model.top_news(API_KEY, country = current_location.countryCode.lowercase(), page_size = 3)
                }
            }

            top_news_view_model.news_list.observe(activity)
            {
                if (it.isNotEmpty()) {

                }
            }

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