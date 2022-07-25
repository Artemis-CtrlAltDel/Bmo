package com.example.bmo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.bmo.adapters.ViewPagerAdapter
import com.example.bmo.databinding.ActivityMainBinding
import com.example.bmo.ui.AllNewsFragment
import com.example.bmo.ui.FavoriteNewsFragment
import com.example.bmo.ui.LocalNewsFragment
import com.example.bmo.viewmodel.NewsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var view_model: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )

            view_model = ViewModelProvider(this@MainActivity)[NewsViewModel::class.java]

            val fragment_titles = arrayListOf(
                AllNewsFragment().TITLE,
                LocalNewsFragment().TITLE,
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