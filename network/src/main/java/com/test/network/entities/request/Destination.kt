package com.test.network.entities.request

import com.google.gson.annotations.SerializedName

data class Destination(
    @SerializedName("regionId")
    val regionId: String
)