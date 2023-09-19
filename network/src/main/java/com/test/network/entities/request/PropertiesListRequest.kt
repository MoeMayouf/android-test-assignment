package com.test.network.entities.request

import com.google.gson.annotations.SerializedName

data class PropertiesListRequest(
    @SerializedName("checkInDate")
    val checkInDate: CheckInOutDate,
    @SerializedName("checkOutDate")
    val checkOutDate: CheckInOutDate,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("destination")
    val destination: Destination,
    @SerializedName("eapid")
    val eaPid: Int,
    @SerializedName("filters")
    val filters: Filters,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("resultsSize")
    val resultsSize: Int,
    @SerializedName("resultsStartingIndex")
    val resultsStartingIndex: Int,
    @SerializedName("rooms")
    val rooms: List<Room>,
    @SerializedName("siteId")
    val siteId: Int,
    @SerializedName("sort")
    val sort: String
)