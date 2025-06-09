package com.example.movies.netwroking.signin

import com.example.movies.models.UserData
import org.json.JSONObject

class SignInMapper {
    fun jsonObject(userData: UserData): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.accumulate("email", userData.email)
        jsonObject.accumulate("password", userData.password)
        return jsonObject
    }
}