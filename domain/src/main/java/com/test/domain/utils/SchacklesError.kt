package com.test.domain.utils
sealed class SchacklesError(cause: Throwable) : Throwable(cause) {
    class ConnectionError(cause: Throwable) : SchacklesError(cause)
    class HttpError(cause: Throwable) : SchacklesError(cause)
    class UnknownError(cause: Throwable) : SchacklesError(cause)
}