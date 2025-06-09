package com.example.movies.netwroking.signin

import org.json.JSONObject
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection

class SignInOutputStreamWriter {
    fun writer(
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
}