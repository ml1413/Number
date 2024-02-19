package com.i.number.reqest.request_random_info

import android.util.Log
import com.i.number.model.ModelNumberInfo
import com.i.number.reqest.MyResource
import com.i.number.reqest.MyRetrofit

class RepositoryRandomInfo(myRetrofit: MyRetrofit) {
    private val apiInterfaceRandomInfo =
        myRetrofit.getRetrofit(baseUrl = BASE_URL).create(ApiInterfaceRandomInfo::class.java)

    suspend fun getRandomInfo(): MyResource<ModelNumberInfo> {
        return try {
            val response = apiInterfaceRandomInfo.getRandomInfo()
            MyResource.Success(data = response)
        } catch (e: Exception) {
            Log.e("RepositoryRandomInfo", "${e.message}")
            MyResource.Error(message = "Failed load info ")
        }
    }

    companion object {
        private const val BASE_URL = "http://numbersapi.com"
    }
}