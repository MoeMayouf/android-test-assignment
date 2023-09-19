package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class DestinationInfo(
    @SerializedName("distanceFromDestination")
    val distanceFromDestination: DistanceFromDestination
)