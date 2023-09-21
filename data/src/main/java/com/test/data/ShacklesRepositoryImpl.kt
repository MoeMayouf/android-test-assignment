package com.test.data

import com.test.database.ShacklesDao
import com.test.domain.model.PropertyList
import com.test.domain.model.PropertyQuery
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
import javax.inject.Singleton

@Singleton
open class ShacklesRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val shacklesDao: ShacklesDao
) : ShacklesRepository {
    override suspend fun getPropertiesList(
        checkedInDate: String,
        checkedOutDate: String,
        adult: Int,
        children: Int
    ): Results<PropertyList> {

        val checkedInDateArray = checkedInDate.split("/")
        val checkedOutDateArray = checkedOutDate.split("/")

        val childList = mutableListOf<Children>()
        for (i in 1..children) {
            childList.add(Children(5))
        }
        val samplePropertiesListRequest =
            propertiesListRequest(checkedInDateArray, checkedOutDateArray, adult, childList)


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

    override suspend fun getSearchQueryList(): List<PropertyQuery> {
        return shacklesDao.getSearchQuery().map { it.toPropertyQuery() }
    }

    override suspend fun insertProperty(propertyQuery: PropertyQuery) {
        return shacklesDao.insertSearchQuery(propertyQuery.toShacklesSearchEntity())
    }

    override suspend fun deleteProperty(propertyQuery: PropertyQuery) {
        shacklesDao.deleteSearchQuery(propertyQuery.toShacklesSearchEntity())
    }

    private fun propertiesListRequest(
        checkedInDateArray: List<String>,
        checkedOutDateArray: List<String>,
        adult: Int,
        childList: MutableList<Children>
    ): PropertiesListRequest {
        return PropertiesListRequest(
            checkInDate = CheckInOutDate(
                day = checkedInDateArray[0].toInt(),
                month = checkedInDateArray[1].toInt(),
                year = checkedInDateArray[2].toInt()
            ),
            checkOutDate = CheckInOutDate(
                day = checkedOutDateArray[0].toInt(),
                month = checkedOutDateArray[1].toInt(),
                year = checkedOutDateArray[2].toInt()
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
                    adults = adult,
                    children = childList
                )
            ),
            siteId = 1,
            sort = "price:asc"
        )
    }


}