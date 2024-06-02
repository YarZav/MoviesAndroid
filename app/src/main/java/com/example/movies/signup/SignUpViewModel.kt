package com.example.movies.signup

import androidx.lifecycle.ViewModel
import com.example.movies.models.UserData
import com.example.movies.netwroking.Networking

class SignUpViewModel(private val fragment: SignUpFragment?): ViewModel() {
    private val networking = Networking()

    fun signUp(name: String, email: String, password: String?) {
        val userData = UserData(name, email, password)

        fragment?.setLoading(true)
        networking.signUp(
            userData,
            completionData = {
                fragment?.setLoading(false)
            },
            completionError = {
                fragment?.setLoading(false)
            }
        )
    }
}