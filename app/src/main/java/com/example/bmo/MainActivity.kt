package com.example.bmo

import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bmo.adapters.ViewPagerAdapter
import com.example.bmo.databinding.ActivityMainBinding
import com.example.bmo.others.API_KEY
import com.example.bmo.others.LocationService
import com.example.bmo.ui.AllNewsFragment
import com.example.bmo.ui.FavoriteNewsFragment
import com.example.bmo.ui.TopNewsFragment
import com.example.bmo.viewmodel.NewsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            val view_model = ViewModelProvider(this@MainActivity)[NewsViewModel::class.java]

            val fragment_titles = arrayListOf(
                AllNewsFragment().TITLE,
                TopNewsFragment().TITLE,
                FavoriteNewsFragment().TITLE
            )

            val view_pager_adapter = ViewPagerAdapter(this@MainActivity)
            viewPager.adapter = view_pager_adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = fragment_titles[position]
            }.attach()
        }
    }
}