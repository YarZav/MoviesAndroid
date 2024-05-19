package com.example.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.movies.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLanding.setOnClickListener {
            moveToLanding()
        }
    }

    private fun moveToLanding() {
        val previousFragment = findNavController().previousBackStackEntry?.destination?.id
        previousFragment?.let {
            when (previousFragment) {
                R.id.landingFragment ->
                    findNavController().popBackStack()
                else ->
                    findNavController().navigate(R.id.action_landingFragment_to_signUpFragment)
            }
        }
    }
}