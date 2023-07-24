package com.glsservice.tg.Apiclient.Service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    fun getRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val okHttpClient = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl("http:/192.168.1.70/api/V1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}