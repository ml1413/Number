package com.i.number.reqest.request_random_info

import com.i.number.model.ModelNumberInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaceRandomInfo {
    @GET("random/math?json")
    suspend fun getRandomInfo(
    ): ModelNumberInfo
}