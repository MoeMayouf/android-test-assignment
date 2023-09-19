package com.test.domain.usecase

import com.test.domain.repository.ShacklesRepository
import com.test.domain.utils.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPropertiesListUseCase @Inject constructor(
    private val shackleRepository: ShacklesRepository
) {
    operator fun invoke(
        checkedInDate: String,
        checkedOutDate: String,
        adults: Int,
        children: Int
    ): Flow<Results<Any>> = flow {
        emit(
            shackleRepository.getPropertiesList(checkedInDate, checkedOutDate, adults, children)
        )
    }
}