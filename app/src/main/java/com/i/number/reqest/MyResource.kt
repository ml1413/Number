package com.i.number.reqest

sealed class MyResource<T>() {
    class Success<T>(val data: T) : MyResource<T>()
    class Error<T>(val message: String) : MyResource<T>()
}