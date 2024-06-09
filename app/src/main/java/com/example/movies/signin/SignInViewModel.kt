package com.example.movies.signin

import androidx.lifecycle.ViewModel
import com.example.movies.netwroking.Networking

class SignInViewModel(private val fragment: SignInFragment?): ViewModel() {
    private val networking = Networking()

    fun signIn() {}
}