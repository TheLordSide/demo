package tg.intaonline.intaonline.ApiClient.service

import com.example.demo.Apiclient.ApiResponse.NotifyCreationResponse
import com.example.demo.Apiclient.ApiResponse.NotifyResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import tg.intaonline.intaonline.ApiClient.ApiResponse.*


interface ApiInterface {
    @FormUrlEncoded
    @POST("auth/login.php")
    fun login(
        @Field("Telcompte") phone: String?,
        @Field("Mdpcompte") password: String?
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("auth/signup.php")
    fun register(
        @Field("Telcompte") phone: String?,
        @Field("Mdpcompte") password: String?
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("notify/create.php")
    fun createnotify(
        @Field("Contentnotif") content: String?,
        @Field("auteur") auteur: String?
    ): Call<NotifyCreationResponse>

    @GET("notify/liste.php")
    fun getNotify(): Call<NotifyResponse>
}