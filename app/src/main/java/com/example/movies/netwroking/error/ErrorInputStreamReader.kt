package com.example.movies.netwroking.error

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection

class ErrorInputStreamReader {
    fun reader(
        connection: HttpURLConnection,
    ) {
        val reader = InputStreamReader(connection.errorStream)
        reader.use { input ->
            val response = StringBuilder()
            val bufferReader = BufferedReader(input)

            bufferReader.forEachLine {
                response.append(it.trim())
            }

            var errorCode = connection.responseCode
            val errorBody = response.toString()

            throw IOException("HTTP error code ${errorCode} & body ${errorBody}")
        }
    }
}