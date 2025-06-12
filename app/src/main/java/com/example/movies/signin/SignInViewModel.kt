package com.example.movies.signin

import androidx.lifecycle.ViewModel
import com.example.movies.models.UserData
import com.example.movies.netwroking.Networking

class SignInViewModel(private val fragment: SignInFragment?): ViewModel() {
    private val networking = Networking()

    fun signIn(userData: UserData) {
        fragment?.setLoading(true)
        networking.signIn(
            userData,
            completionData = {
                fragment?.setLoading(false)
                fragment?.movies()
            },
            completionError = {
                fragment?.setLoading(false)
                fragment?.showError(it)
            }
        )
    }
}