package com.example.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.movies.databinding.FragmentSignUpBinding
import com.example.movies.models.UserData
import com.example.movies.netwroking.Networking


class SignUpFragment : Fragment() {
    private val networking = Networking()

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

        binding.buttonSignUp.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val userData = UserData(name, email, password)

            setLoading(true)
            networking.signUp(
                userData,
                completionData = {
                    activity?.runOnUiThread(Runnable {
                        setLoading(false)
                        Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
                    })
                },
                completionError = {
                    activity?.runOnUiThread(Runnable {
                        setLoading(false)
                        Toast.makeText(activity, it.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                    })
                }
            )
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

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}