package com.test.network.api

import com.test.network.entities.details.PropertiesDetails
import com.test.network.entities.request.PropertiesListRequest
import com.test.network.entities.response.PropertiesListResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("properties/v2/list")
    suspend fun getPropertiesList(
        @Body propertiesListSearchEntity: PropertiesListRequest
    ): PropertiesListResponse

    @POST("properties/v2/detail")
    suspend fun getPropertiesDetails(
        @Body propertiesDetailsRequestEntity: PropertiesDetails
    ): String
}
