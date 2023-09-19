package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class PropertyPrice(
    @SerializedName("lead")
    val lead: Lead,
    @SerializedName("priceMessages")
    val priceMessages: List<PriceMessages>
)