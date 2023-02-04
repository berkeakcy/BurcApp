package com.berkeakcy.burcapp

sealed class UIState<out T> {
    //Loading, Succes, Failure
    object Loading : UIState<Nothing>()
    data class Success<out T>(val data : T): UIState<T>()
    data class Failure(val error: String?): UIState<Nothing>()
}