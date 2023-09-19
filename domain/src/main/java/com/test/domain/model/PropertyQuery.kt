package com.test.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PropertyQuery(
    val id: Int? = null,
    val checkedInDate: String?,
    val checkedOutDate: String?,
    val adults: Int?,
    val children: Int?,
    val time: Long
) : Parcelable