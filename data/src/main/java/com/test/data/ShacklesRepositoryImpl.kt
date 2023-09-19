package com.test.data

import com.test.database.ShacklesDao
import com.test.domain.model.PropertyList
import com.test.domain.repository.ShacklesRepository
import com.test.domain.utils.Results
import com.test.network.api.ApiService
import com.test.network.entities.request.CheckInOutDate
import com.test.network.entities.request.Children
import com.test.network.entities.request.Destination
import com.test.network.entities.request.Filters
import com.test.network.entities.request.Price
import com.test.network.entities.request.PropertiesListRequest
import com.test.network.entities.request.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShacklesRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val schacklesDao: ShacklesDao
) : ShacklesRepository {
    override suspend fun getPropertiesList(
        checkedInDate: String,
        checkedOutDate: String,
        adult: Int,
        children: Int
    ): Results<PropertyList> {
        // TODO: EXTRACT SAMPLE DATA FOR THE TIME BEING
        val samplePropertiesListRequest = PropertiesListRequest(
            checkInDate = CheckInOutDate(
                day = 15,
                month = 9,
                year = 2023
            ),
            checkOutDate = CheckInOutDate(
                day = 22,
                month = 9,
                year = 2023
            ),
            currency = "USD",
            destination = Destination("New York City"),
            eaPid = 12345,
            filters = Filters(
                price = Price(
                    min = 100,
                    max = 500
                )
            ),
            locale = "en_US",
            resultsSize = 10,
            resultsStartingIndex = 0,
            rooms = listOf(
                Room(
                    adults = 1,
                    children = listOf(Children(5))
                ),
                Room(
                    adults = 1,
                    children = listOf(Children(5))
                )
            ),
            siteId = 1,
            sort = "price:asc"
        )


        val response = api.getPropertiesList(samplePropertiesListRequest).toPropertyList()
        return try {
            Results.Success(
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext) { response }
            )
        } catch (e: Exception) {
            Results.Error(
                when (e) {
                    // TODO: IMPLEMENT PROPER ERROR HANDLING, PLACEHOLDER FOR NOW
                    is IOException -> "no connection error"
                    is HttpException -> "Unexpected response"
                    else -> "Unknown error"
                }
            )
        }
    }

}