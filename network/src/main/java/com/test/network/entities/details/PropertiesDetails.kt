package com.test.network.entities.details

import com.google.gson.annotations.SerializedName

data class PropertiesDetails(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("eapid")
    val eaPid: Int,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("propertyId")
    val propertyId: String,
    @SerializedName("siteId")
    val siteId: Int
)