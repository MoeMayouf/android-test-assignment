package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class DistanceFromDestination(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)