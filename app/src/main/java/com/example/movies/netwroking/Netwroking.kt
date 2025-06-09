package com.example.movies.netwroking

import com.example.movies.models.Jwt
import com.example.movies.models.UserData
import com.example.movies.netwroking.signin.SignInConnection
import com.example.movies.netwroking.signin.SignInInputStreamReader
import com.example.movies.netwroking.signin.SignInMapper
import com.example.movies.netwroking.signin.SignInOutputStreamWriter
import com.example.movies.netwroking.signup.SignUpConnection
import com.example.movies.netwroking.signup.SignUpInputStreamReader
import com.example.movies.netwroking.signup.SignUpMapper
import com.example.movies.netwroking.signup.SignUpOutputStreamWriter
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection

class Networking {
    companion object {
        const val baseUrl = "http://192.168.0.107:3000"
    }

    fun signUp(
        userData: UserData,
        completionData: (UserData) -> Unit,
        completionError: (Exception) -> Unit
    ) {
        Thread(Runnable {
            val connection = SignUpConnection().connection

            try {
                val jsonObject = SignUpMapper().jsonObject(userData)
                SignUpOutputStreamWriter().writer(connection, jsonObject)
                SignUpInputStreamReader().reader(connection, completionData)
            } catch (exception: Exception) {
                completionError(exception)
            } finally {
                connection.disconnect()
            }
        }).start()
    }

    fun signIn(
        userData: UserData,
        completionData: (Jwt) -> Unit,
        completionError: (Exception) -> Unit
    ) {
        Thread(Runnable {
            val connection = SignInConnection().connection

            try {
                val jsonObject = SignInMapper().jsonObject(userData)
                SignInOutputStreamWriter().writer(connection, jsonObject)
                SignInInputStreamReader().reader(connection, completionData)
            } catch (exception: Exception) {
                completionError(exception)
            } finally {
                connection.disconnect()
            }
        }).start()
    }
}