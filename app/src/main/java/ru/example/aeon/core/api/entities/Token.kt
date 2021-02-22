package ru.example.aeon.core.api.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token(

    @Expose
    @SerializedName("token")
    var token: String?

)