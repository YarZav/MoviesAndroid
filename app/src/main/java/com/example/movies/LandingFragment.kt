package com.example.movies

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
            val rootWidth = binding.root.measuredWidth

            val params = binding.landingOneWeek.layoutParams
            params.width = (rootWidth - 18 * 2) / 3
            binding.landingOneWeek.setLayoutParams(params)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLearnMore.setOnClickListener {
            val childView = binding.landingAboutProjectBlock
            binding.fragmentLandingScroll.smoothScrollTo(0, childView.top);
        }
    }
}