package ru.example.aeon.core.api.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error(

    @Expose
    @SerializedName("error_code")
    var errorCode: String,

    @Expose
    @SerializedName("error_msg")
    var errorMsg: String

)