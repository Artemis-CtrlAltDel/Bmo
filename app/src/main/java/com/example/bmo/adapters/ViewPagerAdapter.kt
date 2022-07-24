package com.example.bmo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bmo.ui.AllNewsFragment
import com.example.bmo.ui.FavoriteNewsFragment
import com.example.bmo.ui.TopNewsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> AllNewsFragment()
            1 -> TopNewsFragment()
            else -> FavoriteNewsFragment()
        }
    }
}