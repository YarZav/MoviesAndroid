package com.example.movies.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.movies.R
import com.example.movies.databinding.FragmentSignInBinding
import com.example.movies.models.UserData

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

        viewModel = SignInViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLanding.setOnClickListener {
            moveToLanding()
        }

        binding.buttonSignIn.setOnClickListener {
            signIn()
        }

        binding.buttonSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun moveToLanding() {
        val previousFragment = findNavController().previousBackStackEntry?.destination?.id
        previousFragment?.let {
            when (previousFragment) {
                R.id.landingFragment ->
                    findNavController().popBackStack()
                else ->
                    findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }
    }

    private fun signIn() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

//        viewModel.signIn(name, email, password)
    }

    private fun signUp() {
        activity?.runOnUiThread(Runnable {
            val previousFragment = findNavController().previousBackStackEntry?.destination?.id
            previousFragment?.let {
                when (previousFragment) {
                    R.id.signUpFragment ->
                        findNavController().popBackStack()
                    else ->
                        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
                }
            }
        })
    }

    fun setLoading(isLoading: Boolean) {
        activity?.runOnUiThread(Runnable {
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }
}