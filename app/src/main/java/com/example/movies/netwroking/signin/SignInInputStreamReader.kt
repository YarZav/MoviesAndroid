package com.example.movies.netwroking.signin

import com.example.movies.models.Jwt
import com.example.movies.netwroking.error.ErrorInputStreamReader
import com.example.movies.netwroking.isOk
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection

class SignInInputStreamReader {
    fun reader(
        connection: HttpURLConnection,
        completion: (Jwt) -> Unit
    ) {
        if (connection.isOk)
            successReader(connection, completion)
        else
            errorReader(connection)
    }

    private fun successReader(
        connection: HttpURLConnection,
        completion: (Jwt) -> Unit
    ) {
        val reader = InputStreamReader(connection.inputStream)
        reader.use { input ->
            val response = StringBuilder()
            val bufferReader = BufferedReader(input)

            bufferReader.forEachLine {
                response.append(it.trim())
            }

            val responseString = response.toString()
            val jwt = Gson().fromJson(responseString, Jwt::class.java)

            completion(jwt)
        }
    }

    private fun errorReader(connection: HttpURLConnection) {
        ErrorInputStreamReader().reader(connection)
    }
}