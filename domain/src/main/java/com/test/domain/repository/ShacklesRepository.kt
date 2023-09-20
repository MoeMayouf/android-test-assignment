package com.test.domain.repository

import com.test.domain.model.PropertyList
import com.test.domain.model.PropertyQuery
import com.test.domain.utils.Results

interface ShacklesRepository {
    suspend fun getPropertiesList(
        checkedInDate: String,
        checkedOutDate: String,
        adult: Int,
        children: Int
    ): Results<PropertyList>
    suspend fun getSearchQueryList(): List<PropertyQuery>
    suspend fun insertProperty(propertyQuery: PropertyQuery)
    suspend fun deleteProperty(propertyQuery: PropertyQuery)
}