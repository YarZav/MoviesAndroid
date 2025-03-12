package com.example.movies.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val name: String,
    val email: String,
    val password: String?
): Parcelable