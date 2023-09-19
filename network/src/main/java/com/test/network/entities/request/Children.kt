package com.test.network.entities.request

import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("age")
    val age: Int
)