package com.example.bmo.ui

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmo.adapters.AllNewsAdapter
import com.example.bmo.adapters.OnItemClick
import com.example.bmo.databinding.FragmentLocalNewsBinding
import com.example.bmo.others.API_KEY
import com.example.bmo.others.LocationService
import com.example.bmo.viewmodel.NewsViewModel

class LocalNewsFragment : Fragment() {

    private val TAG = "TopNewsFragment"

    val TITLE = "Locals"

    private var _binding : FragmentLocalNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var location_service: LocationService
    private lateinit var view_model: NewsViewModel

    private lateinit var local_news_adapter: AllNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = requireContext()
        val activity = requireActivity()

        location_service = LocationService(activity)
        view_model = activity.let { ViewModelProvider(it)[NewsViewModel::class.java] }

        _binding = FragmentLocalNewsBinding.inflate(inflater, container, false)
        binding.apply {

            local_news_adapter = AllNewsAdapter(
                1,
                arrayListOf(),
                object: OnItemClick {
                    override fun on_favorite_click(position: Int) {

                    }
                }
            )

            location_service.location_update {
                val last_location = it.lastLocation
                val latitude = last_location.latitude
                val longitude = last_location.longitude

                val current_location = Geocoder(context)
                    .getFromLocation(latitude, longitude, 1)[0]

                current_location.apply {
                    view_model.top_news(API_KEY, country = countryCode.lowercase())
                }
            }

            view_model.top_news_list.observe(activity)
            {
                if (it.isNotEmpty()) {
                    local_news_adapter.set_items(it)
                    Log.e(TAG, "local news requested")
                }
            }

            localNewsRecycler.adapter = local_news_adapter
            localNewsRecycler.setHasFixedSize(true)
            localNewsRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

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