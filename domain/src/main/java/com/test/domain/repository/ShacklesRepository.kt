package com.test.domain.repository

import com.test.domain.model.PropertyList
import com.test.domain.utils.Results

interface ShacklesRepository {
    suspend fun getPropertiesList(
        checkedInDate: String,
        checkedOutDate: String,
        adult: Int,
        children: Int
    ): Results<PropertyList>
}