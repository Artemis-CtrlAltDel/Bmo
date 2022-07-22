package com.example.bmo

import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.bmo.databinding.ActivityMainBinding
import com.example.bmo.others.API_KEY
import com.example.bmo.others.LocationService
import com.example.bmo.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private var location_service: LocationService = LocationService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            val view_model = ViewModelProvider(this@MainActivity)[NewsViewModel::class.java]

            location_service.location_update {
                val last_location = it.lastLocation
                val latitude = last_location.latitude
                val longitude = last_location.longitude

                val current_location = Geocoder(applicationContext)
                    .getFromLocation(latitude, longitude, 1)[0]

                current_location.apply {
                    view_model.top_news(API_KEY, country = current_location.countryCode.lowercase())
                }
            }

            view_model.top_news_list.observe(this@MainActivity)
            {
                Log.e(TAG, "mohamed : $it")
            }

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
}