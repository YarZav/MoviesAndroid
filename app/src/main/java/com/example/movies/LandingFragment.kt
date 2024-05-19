package com.example.movies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.movies.databinding.FragmentLandingBinding


class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

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
            openGithub()
        }

        binding.buttonSignUp.setOnClickListener {
            openSignUp()
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

    private fun openGithub() {
        val uriString = "https://github.com/YarZav"
        val uri = Uri.parse("https://github.com/YarZav")
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(browserIntent)
    }

    private fun openSignUp() {
        findNavController().navigate(R.id.action_landingFragment_to_signUpFragment)
    }
}