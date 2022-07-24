package com.example.bmo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmo.databinding.ActivityNewsCardBinding

class NewsCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNewsCardBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
        }

    }
}