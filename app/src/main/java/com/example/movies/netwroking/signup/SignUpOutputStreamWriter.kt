package com.example.movies.netwroking.signup

import org.json.JSONObject
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection

class SignUpOutputStreamWriter {
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