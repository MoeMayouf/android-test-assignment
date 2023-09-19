package com.test.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PropertyList(
    val results: List<Property>
) : Parcelable

@Parcelize
data class Property(
    val id: String,
    val name: String?,
    val propertyImage: String?,
    val priceString: String?,
    val locationName: String?,
    val star: String?
) : Parcelable