package com.example.movies.netwroking.signup

import com.example.movies.models.UserData
import com.example.movies.netwroking.error.ErrorInputStreamReader
import com.example.movies.netwroking.isOk
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection

class SignUpInputStreamReader {
    fun reader(
        connection: HttpURLConnection,
        completion: (UserData) -> Unit
    ) {
        if (connection.isOk)
            successReader(connection, completion)
        else
            errorReader(connection)
    }

    private fun successReader(
        connection: HttpURLConnection,
        completion: (UserData) -> Unit
    ) {
        val reader = InputStreamReader(connection.inputStream)
        reader.use { input ->
            val response = StringBuilder()
            val bufferReader = BufferedReader(input)

            bufferReader.forEachLine {
                response.append(it.trim())
            }

            val responseString = response.toString()
            val userData = Gson().fromJson(responseString, UserData::class.java)

            completion(userData)
        }
    }

    private fun errorReader(connection: HttpURLConnection) {
        ErrorInputStreamReader().reader(connection)
    }
}