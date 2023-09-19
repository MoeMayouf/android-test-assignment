package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class PropertiesListResponseData(
    @SerializedName("propertySearch")
    val propertySearch: PropertySearchEntity
)