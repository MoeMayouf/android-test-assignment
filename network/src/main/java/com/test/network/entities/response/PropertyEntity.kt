package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class PropertyEntity(
    @SerializedName("availability")
    val availability: Availability,
    @SerializedName("destinationInfo")
    val destinationInfo: DestinationInfo,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: PropertyPrice,
    @SerializedName("propertyImage")
    val propertyImage: PropertyImage,
    @SerializedName("neighborhood")
    val neighbourhood: NeighbourHood,
    @SerializedName("star")
    val star: String
)