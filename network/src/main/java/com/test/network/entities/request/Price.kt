package com.test.network.entities.request

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("max")
    val max: Int,
    @SerializedName("min")
    val min: Int
)