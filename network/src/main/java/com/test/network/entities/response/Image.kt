package com.test.network.entities.response

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String
)