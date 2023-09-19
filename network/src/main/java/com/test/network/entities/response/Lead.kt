package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class Lead(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("formatted")
    val formatted: String
)