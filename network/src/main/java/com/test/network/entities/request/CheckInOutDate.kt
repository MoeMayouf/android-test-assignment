package com.test.network.entities.request

import com.google.gson.annotations.SerializedName

data class CheckInOutDate(
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("year")
    val year: Int
)