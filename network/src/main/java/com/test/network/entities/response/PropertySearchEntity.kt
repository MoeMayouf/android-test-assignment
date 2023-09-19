package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class PropertySearchEntity(
    @SerializedName("properties")
    val properties: List<PropertyEntity>
)