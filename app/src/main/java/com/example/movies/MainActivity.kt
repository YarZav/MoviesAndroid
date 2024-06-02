package com.example.movies

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.netwroking.NetworkChecker

class MainActivity : AppCompatActivity() {
    private val networkChecker by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkChecker(getSystemService(ConnectivityManager::class.java))
        } else {
            TODO("VERSION.SDK_INT < M")
        }
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}