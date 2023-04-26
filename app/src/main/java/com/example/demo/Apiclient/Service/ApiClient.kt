package tg.intaonline.intaonline.ApiClient.service

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
            .baseUrl("https://gls.devbase.tg/demo/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}