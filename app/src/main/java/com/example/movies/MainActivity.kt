package com.example.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportFragmentManager.commit {
//            setCustomAnimations(
//                R.anim.slide_in, // enter
//                R.anim.fade_out, // exit
//                R.anim.fade_in, // popEnter
//                R.anim.slide_out // popExit
//            )
//            replace(R.id.navHostFragmentContainerView, fragment)
//            addToBackStack(null)
//        }
    }
}