package com.test.data

import com.test.database.ShacklesSearchEntity
import com.test.domain.model.Property
import com.test.domain.model.PropertyList
import com.test.domain.model.PropertyQuery
import com.test.network.entities.response.PropertiesListResponse

fun PropertiesListResponse.toPropertyList() =
    PropertyList(results = data.propertySearch.properties.map {
        Property(
            it.id,
            it.name,
            it.propertyImage.image.url,
            "${it.price.lead.formatted} ${it.price.priceMessages[0].value}",
            it.neighbourhood.name,
            it.star
        )
    })

fun PropertyQuery.toShacklesSearchEntity() =
    ShacklesSearchEntity(id, checkedInDate, checkedOutDate, adults, children, time)

fun ShacklesSearchEntity.toPropertyQuery() =
    PropertyQuery(id, checkedInDate, checkedOutDate, adults, children, date)