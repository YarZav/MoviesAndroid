package com.example.movies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movies.databinding.FragmentLandingBinding


class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingBinding.inflate(layoutInflater)

        binding.root.post(Runnable {
            updateOneWeekBlockWidth()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLearnMore.setOnClickListener {
            scrollToLearnMoreBlock()
        }

        binding.buttonGithub.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/YarZav"))
            startActivity(browserIntent)
        }
    }

    private fun scrollToLearnMoreBlock() {
        val childView = binding.landingAboutProjectBlock
        binding.fragmentLandingScroll.smoothScrollTo(0, childView.top)
    }

    private fun updateOneWeekBlockWidth() {
        val rootWidth = binding.root.measuredWidth
        val oneWeekLayoutParams = binding.landingOneWeek.layoutParams
        oneWeekLayoutParams.width = (rootWidth - 18 * 2) / 3
        binding.landingOneWeek.setLayoutParams(oneWeekLayoutParams)
    }
}