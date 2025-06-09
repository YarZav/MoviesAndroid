package com.example.movies.netwroking

import java.net.HttpURLConnection

val HttpURLConnection.isOk: Boolean
    get() {
        val successCodes = listOf(
            HttpURLConnection.HTTP_OK,
            HttpURLConnection.HTTP_CREATED,
            HttpURLConnection.HTTP_ACCEPTED,
            HttpURLConnection.HTTP_NOT_AUTHORITATIVE,
            HttpURLConnection.HTTP_NO_CONTENT,
            HttpURLConnection.HTTP_RESET,
            HttpURLConnection.HTTP_PARTIAL,
        )
        return responseCode in successCodes
    }