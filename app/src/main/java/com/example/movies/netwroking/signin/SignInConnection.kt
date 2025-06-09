package com.example.movies.netwroking.signin

import com.example.movies.netwroking.Networking
import java.net.HttpURLConnection
import java.net.URL

class SignInConnection {

    val connection: HttpURLConnection
        get() {
            val connection = URL(Networking.baseUrl + "/signin").openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")
            return connection
        }
}

