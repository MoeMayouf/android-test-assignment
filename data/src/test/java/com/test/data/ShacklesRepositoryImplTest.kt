package com.test.data

import com.test.database.ShacklesDao
import com.test.domain.model.PropertyQuery
import com.test.domain.utils.Results
import com.test.network.api.ApiService
import com.test.network.entities.response.PropertiesListResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

class ShacklesRepositoryImplTest {

    private lateinit var repository: ShacklesRepositoryImpl
    private val apiService: ApiService = mock()
    private val shacklesDao: ShacklesDao = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = ShacklesRepositoryImpl(apiService, shacklesDao)
    }

    @Test
    fun `test getPropertiesList success`() = runBlocking {
        // Arrange

        val sampleResponse = PropertiesListResponse(mock()) // replace with actual sample data
        whenever(apiService.getPropertiesList(any())).thenReturn(sampleResponse)

        whenever(apiService.getPropertiesList(any())).thenReturn(sampleResponse)

        // Act
        val result = repository.getPropertiesList("2023-09-15", "2023-09-22", 2, 2)

        // Assert
        assert(result is Results.Success)
        assertEquals(sampleResponse, (result as Results.Success).data)
        verify(apiService).getPropertiesList(any())
    }

    @Test
    fun `test getPropertiesList failure`() = runBlocking {
        // Arrange
        whenever(apiService.getPropertiesList(any())).thenThrow(IOException())

        // Act
        val result = repository.getPropertiesList("2023-09-15", "2023-09-22", 2, 2)

        // Assert
        assert(result is Results.Error)
        assertEquals("no connection error", (result as Results.Error))
        verify(apiService).getPropertiesList(any())
    }

    @Test
    fun `test getSearchQueryList`() = runBlocking {
        // Arrange
        val sampleResponse = listOf(
            PropertyQuery(
                9,
                "02/08/2023",
                "02/09/2023",
                2,
                2,
                3
            )
        ) // replace with actual sample data
        whenever(shacklesDao.getSearchQuery()).thenReturn(mock())

        // Act
        val result = repository.getSearchQueryList()

        // Assert
        assertEquals(sampleResponse, result)
        verify(shacklesDao).getSearchQuery()
    }

    // Similar tests can be written for insertProperty and deleteProperty methods
}
