package ru.example.aeon.core.functional

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.example.aeon.core.api.entities.Error

data class Result<T>(

    @Expose
    @SerializedName("success")
    var success: Boolean,

    @Expose
    @SerializedName("response")
    var response: T?,

    @Expose
    @SerializedName("error")
    var error: Error?

)