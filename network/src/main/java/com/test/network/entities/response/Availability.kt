package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class Availability(
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("minRoomsLeft")
    val minRoomsLeft: Int
)