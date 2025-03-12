package com.example.movies.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.movies.R
import com.example.movies.databinding.FragmentSignInBinding
import com.example.movies.models.UserData
import java.lang.Exception

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel

    private val args: SignInFragmentArgs by navArgs()

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

        binding.emailEditText.setText(args.userData?.email)

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
        findNavController().popBackStack()
    }

    private fun signIn() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val userData = UserData("", email, password)

        viewModel.signIn(userData)
    }

    private fun signUp() {
        findNavController().popBackStack()
        findNavController().navigate(R.id.action_landingFragment_to_signUpFragment)
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
}