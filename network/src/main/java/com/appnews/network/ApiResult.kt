package com.appnews.network

import retrofit2.Response
import java.net.HttpURLConnection

sealed class ApiResult<out T> {
    data class Success<T>(val value: T) : ApiResult<T>()
    data class Failure(val statusCode: Int, val statusDesc: String?) : ApiResult<Nothing>()
}

fun <T: Any> Response<T>.parseResult() : ApiResult<T>{
    if(isSuccessful){
        val body = body()
        if(body != null){
            return ApiResult.Success(body)
        }
    } else {
        val errorDesc = errorBody().toString()

        return ApiResult.Failure(code(),errorDesc)
    }
    return ApiResult.Failure(HttpURLConnection.HTTP_INTERNAL_ERROR,null)
}