package ru.example.aeon.core.api.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Payment(

    @Expose
    @SerializedName("desc")
    var desc: String?,

    @Expose
    @SerializedName("amount")
    var amount: String?,

    @Expose
    @SerializedName("currency")
    var currency: String?,

    @Expose
    @SerializedName("created")
    var created: Long?

)