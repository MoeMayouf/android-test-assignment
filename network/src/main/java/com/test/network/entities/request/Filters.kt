package com.test.network.entities.request

import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("price")
    val price: Price
)