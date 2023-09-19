package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class NeighbourHood(
    @SerializedName("name")
    val name: String
)