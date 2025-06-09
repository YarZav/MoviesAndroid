package com.example.movies.netwroking.signup

import com.example.movies.models.UserData
import org.json.JSONObject

class SignUpMapper {
    fun jsonObject(userData: UserData): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("name", userData.name)
        jsonObject.accumulate("email", userData.email)
        jsonObject.accumulate("password", userData.password)
        return jsonObject
    }
}