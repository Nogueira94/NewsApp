package com.appnews.domain.model

sealed class StateView<out T> {
    object Loading : StateView<Nothing>()
    data class DataLoaded<T>(val data: T) : StateView<T>()
    data class Error(val e: Throwable) : StateView<Nothing>()
}