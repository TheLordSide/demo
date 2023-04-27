package tg.intaonline.intaonline.ApiClient.service


import com.example.demo.Apiclient.ApiResponse.ClientListeResponse
import com.example.demo.Apiclient.ApiResponse.ClientResponse
import com.example.demo.Apiclient.ApiResponse.CompteListeResponse
import com.example.demo.Apiclient.ApiResponse.CompteUpdateResponse
import com.example.demo.Apiclient.ApiResponse.NotifyCreationResponse
import com.example.demo.Apiclient.ApiResponse.NotifyResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import tg.intaonline.intaonline.ApiClient.ApiResponse.*
import java.util.Date


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

    @FormUrlEncoded
    @POST("auth/edit.php")
    fun editcompte(
        @Field("Telcompte") auteur: String?
    ): Call<CompteUpdateResponse>

    @FormUrlEncoded
    @POST("client/ajouter.php")
    fun createClient(
        @Field("Nomclient") nom: String?,
        @Field("Prenomclient") prenom: String?,
        @Field("Sexeclient") sex: String?,
        @Field("Naissanceclient") date: String?,
        @Field("Fachatclient") fachat: String?,
        @Field("Villeclient") ville: String?,
        @Field("Quartierclient") qtier: String?,
        @Field("Paysclient") pays: String?,
        @Field("Telcompte") tel: String?
    ): Call<ClientResponse>

    @GET("notify/liste.php")
    fun getNotify(): Call<NotifyResponse>

    @GET("client/liste.php")
    fun getList(): Call<ClientListeResponse>
    @GET("auth/liste.php")
    fun getAccounts(): Call<CompteListeResponse>
}