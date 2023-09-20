package com.test.domain.usecase

import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import com.test.domain.model.PropertyQuery
import com.test.domain.repository.ShacklesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchQueryUseCase @Inject constructor(
    private val shackleRepository: ShacklesRepository
) {
    operator fun invoke(): Flow<List<PropertyQuery>> = flow {
        emit(
            shackleRepository.getSearchQueryList()
        )
    }
}