package com.example.movies.netwroking

import com.example.movies.models.Jwt
import com.example.movies.models.UserData
import com.google.gson.Gson
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class Networking {
    private val baseUrl = "http://192.168.0.107:3000"

    fun signUp(
        userData: UserData,
        completionData: (UserData) -> Unit,
        completionError: (Exception) -> Unit
    ) {
        // Запускаем новый поток
        Thread(Runnable {
            val connection = signUpHttpURLConnection()

            try {
                val jsonObject = signUpJsonObject(userData)
                signUpOutputStreamWriter(connection, jsonObject)

                signUpDataInputStreamReader(connection, completionData)
            } catch (exception: Exception) {
                completionError(exception)
            }

            connection.disconnect()
        }).start()
    }

    fun signIn(
        userData: UserData,
        completionData: (Jwt) -> Unit,
        completionError: (Exception) -> Unit
    ) {
        // Запускаем новый поток
        Thread(Runnable {
            val connection = signInHttpURLConnection()

            try {
                val jsonObject = signInJsonObject(userData)
                signInOutputStreamWriter(connection, jsonObject)

                signInDataInputStreamReader(connection, completionData)
            } catch (exception: Exception) {
                completionError(exception)
            }

            connection.disconnect()
        }).start()
    }

    private fun signUpHttpURLConnection(): HttpURLConnection {
        val connection = URL(baseUrl + "/signup").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.connectTimeout = 3000
        connection.readTimeout = 3000

        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")

        return connection
    }

    private fun signInHttpURLConnection(): HttpURLConnection {
        val connection = URL(baseUrl+ "/signin").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.connectTimeout = 3000
        connection.readTimeout = 3000

        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")

        return connection
    }

    private fun signUpJsonObject(userData: UserData): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("name", userData.name)
        jsonObject.accumulate("email", userData.email)
        jsonObject.accumulate("password", userData.password)
        return jsonObject
    }

    private fun signInJsonObject(userData: UserData): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("email", userData.email)
        jsonObject.accumulate("password", userData.password)
        return jsonObject
    }

    private fun signUpOutputStreamWriter(
        connection: HttpURLConnection,
        jsonObject: JSONObject
    ) {
        val writer = OutputStreamWriter(connection.outputStream, "UTF-8")
        writer.use { output ->
            val request = jsonObject.toString()
            val bufferWriter = BufferedWriter(output)

            bufferWriter.write(request)

            bufferWriter.flush()
            bufferWriter.close()
            writer.close()
        }
    }

    private fun signInOutputStreamWriter(
        connection: HttpURLConnection,
        jsonObject: JSONObject
    ) {
        val writer = OutputStreamWriter(connection.outputStream, "UTF-8")
        writer.use { output ->
            val request = jsonObject.toString()
            val bufferWriter = BufferedWriter(output)

            bufferWriter.write(request)

            bufferWriter.flush()
            bufferWriter.close()
            writer.close()
        }
    }

    private fun signUpDataInputStreamReader(
        connection: HttpURLConnection,
        completionData: (UserData) -> Unit
    ) {
        val reader = InputStreamReader(connection.inputStream)
        reader.use { input ->
            val response = StringBuilder()
            val bufferReader = BufferedReader(input)

            bufferReader.forEachLine {
                response.append(it.trim())
            }

            val responseString = response.toString()
            val gson = Gson()
            val userData = gson.fromJson(responseString, UserData::class.java)
            completionData(userData)
        }
    }

    private fun signInDataInputStreamReader(
        connection: HttpURLConnection,
        completionData: (Jwt) -> Unit
    ) {
        val reader = InputStreamReader(connection.inputStream)
        reader.use { input ->
            val response = StringBuilder()
            val bufferReader = BufferedReader(input)

            bufferReader.forEachLine {
                response.append(it.trim())
            }

            val responseString = response.toString()
            val gson = Gson()
            val jwt = gson.fromJson(responseString, Jwt::class.java)
            completionData(jwt)
        }
    }
}