package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class PropertiesListResponse(
    @SerializedName("data")
    val data: PropertiesListResponseData
)