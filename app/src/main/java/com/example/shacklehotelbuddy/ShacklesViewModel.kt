package com.example.shacklehotelbuddy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.model.Property
import com.test.domain.model.PropertyList
import com.test.domain.model.PropertyQuery
import com.test.domain.usecase.GetPropertiesListUseCase
import com.test.domain.usecase.GetPropertyQueryUseCase
import com.test.domain.usecase.InsertPropertyQueryUseCase
import com.test.domain.utils.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShacklesViewModel @Inject constructor(
    private val getPropertiesListUseCase: GetPropertiesListUseCase,
    private val getPropertyQueryUseCase: GetPropertyQueryUseCase,
    private val insertPropertyQueryUseCase: InsertPropertyQueryUseCase
) : ViewModel() {

    private val _propertiesList = MutableStateFlow(emptyList<Property>())
    val propertiesList get() = _propertiesList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    private val _propertyQueryList = MutableStateFlow(emptyList<PropertyQuery>())
    val propertyQueryList get() = _propertyQueryList.asStateFlow()

    init {
        fetchPropertyQueryList()
    }

    fun fetchPropertiesList(
        checkInDate: String,
        checkOutDate: String,
        adult: Int,
        children: Int
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            getPropertiesListUseCase(
                checkedInDate = checkInDate,
                checkedOutDate = checkOutDate,
                adults = adult,
                children = children
            ).collect { response ->
                when (response) {
                    is Results.Success -> {
                        _propertiesList.value = (response.data as PropertyList).results
                        _isLoading.value = false
                    }

                    is Results.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    private fun fetchPropertyQueryList() {
        viewModelScope.launch {
            getPropertyQueryUseCase().collect { response ->
                _propertyQueryList.value = response
            }
        }
    }

    fun insertPropertyQuery(searchQuery: PropertyQuery) {
        viewModelScope.launch {
            insertPropertyQueryUseCase(searchQuery).collect {
                fetchPropertyQueryList()
            }
        }
    }

}