package com.example.uibasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uibasics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customView.setOnClickListener {
            binding.customView.runCircleAnimation(Math.random().toFloat() * 1f)
        }
    }
}