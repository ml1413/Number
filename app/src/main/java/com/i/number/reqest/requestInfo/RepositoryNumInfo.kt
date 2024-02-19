package com.i.number.reqest.requestInfo

import android.util.Log
import com.i.number.model.ModelNumberInfo
import com.i.number.reqest.MyResource
import com.i.number.reqest.MyRetrofit

class RepositoryNumInfo(myRetrofit: MyRetrofit) {
    private val apiInterfaceNumInfo =
        myRetrofit.getRetrofit(baseUrl = BASE_URL).create(ApiInterfaceNumInfo::class.java)

    suspend fun getNumberInfo(number: String):MyResource<ModelNumberInfo> {
       return try {
            val response = apiInterfaceNumInfo.getInfoNumber(number = number)
           MyResource.Success(data = response)
        } catch (e: Exception) {
            Log.e("RepositoryNumInfo", "${e.message}")
            MyResource.Error(message = "Failed load info ")
        }

    }

    companion object {
        private const val BASE_URL = "http://numbersapi.com"
    }
}