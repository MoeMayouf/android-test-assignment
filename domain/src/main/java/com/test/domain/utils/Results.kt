package com.test.domain.utils

sealed class Results<out T> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val error: SchacklesError) : Results<Nothing>()
}