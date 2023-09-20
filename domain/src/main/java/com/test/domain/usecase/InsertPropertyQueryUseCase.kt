package com.test.domain.usecase

import com.test.domain.model.PropertyQuery
import com.test.domain.repository.ShacklesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertPropertyQueryUseCase @Inject constructor(
    private val shacklesRepository: ShacklesRepository
) {
    operator fun invoke(searchQuery: PropertyQuery): Flow<Unit> = flow {
        emit(
            shacklesRepository.insertProperty(searchQuery)
        )
    }
}