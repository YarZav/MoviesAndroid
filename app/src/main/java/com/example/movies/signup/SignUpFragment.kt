package com.example.movies.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.movies.LandingFragmentDirections
import com.example.movies.R
import com.example.movies.databinding.FragmentSignUpBinding
import com.example.movies.models.UserData
import com.example.movies.signin.SignInFragment
import com.example.movies.signin.SignInFragmentDirections
import java.lang.Exception

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

        viewModel = SignUpViewModel(this)
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
            signUp()
        }

        binding.buttonSignIn.setOnClickListener {
            signIn(null)
        }
    }

    private fun moveToLanding() {
        findNavController().popBackStack()
    }

    private fun signUp() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        viewModel.signUp(name, email, password)
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

    fun showError(error: Exception) {
        activity?.runOnUiThread(Runnable {
            Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
        })
    }

    fun signIn(userData: UserData?) {
        activity?.runOnUiThread(Runnable {
            findNavController().popBackStack()
            val direction =
                LandingFragmentDirections.actionLandingFragmentToSignInFragment(userData)
            findNavController().navigate(direction)
        })
    }
}