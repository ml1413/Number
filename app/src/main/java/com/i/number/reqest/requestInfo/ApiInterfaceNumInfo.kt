package com.i.number.reqest.requestInfo

import com.i.number.model.ModelNumberInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaceNumInfo {
    @GET("{number}/math?json")
    suspend fun getInfoNumber(
        @Path("number") number: String
    ): ModelNumberInfo
}