package com.example.movies.netwroking.signup

import com.example.movies.netwroking.Networking
import java.net.HttpURLConnection
import java.net.URL

class SignUpConnection {

    val connection: HttpURLConnection
        get() {
            val connection = URL(Networking.baseUrl + "/signup").openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")
            return connection
        }
}

