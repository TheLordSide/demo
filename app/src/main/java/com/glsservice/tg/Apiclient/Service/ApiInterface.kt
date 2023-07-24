package tg.intaonline.intaonline.ApiClient.service


import com.glsservice.tg.Apiclient.ApiResponse.AgentListe
import com.glsservice.tg.Apiclient.ApiResponse.AgentListeResponse
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.ApiResponse.AskQuestionResponse
import com.glsservice.tg.Apiclient.ApiResponse.ClientListeResponse
import com.glsservice.tg.Apiclient.ApiResponse.ClientResponse
import com.glsservice.tg.Apiclient.ApiResponse.CompteListeResponse
import com.glsservice.tg.Apiclient.ApiResponse.CompteUpdateResponse
import com.glsservice.tg.Apiclient.ApiResponse.NotifyCreationResponse
import com.glsservice.tg.Apiclient.ApiResponse.NotifyResponse
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListeResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
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

    @FormUrlEncoded
    @POST("messages/ask.php")
    fun askmessage(
        @Field("QuestionClient") question: String?,
        @Field("TelClient") Tel: String?
    ): Call<AskQuestionResponse>

    @FormUrlEncoded
    @POST("messages/answermessage.php")
    fun answerAdmin(
        @Field("ReponseAdmin") reponse: String?,
        @Field("Ticket") ticket: String?,
        @Field("TelClient") Tel: String?
    ): Call<AnswerResponse>

    @FormUrlEncoded
    @POST("commerciaux/ajouter.php")
    fun createAgent(
        @Field("nom") nom: String?,
        @Field("ville") ville: String?,
        @Field("quartier") quartier: String?,
        @Field("tel") tel: String?
    ): Call<AnswerResponse>

    @FormUrlEncoded
    @POST("messages/sendmessage.php")
    fun sendmessage(
        @Field("QuestionClient") reponse: String?,
        @Field("Ticket") ticket: String?,
        @Field("TelClient") Tel: String?
    ): Call<AnswerResponse>

    @FormUrlEncoded
    @POST("messages/receivemessage.php")
    fun receivemessage(
        @Field("ReponseAdmin") reponse: String?,
        @Field("Ticket") ticket: String?,
        @Field("TelClient") Tel: String?
    ): Call<AnswerResponse>

    @FormUrlEncoded
    @POST("journaux/transfert.php")
    fun transfert(
        @Field("Telcommercial") Telcommercial: String?,
        @Field("Date") Date: String?,
        @Field("Qte") Qte: String?
    ): Call<AnswerResponse>

    @FormUrlEncoded
    @POST("auth/edit.php")
    fun editcompte(
        @Field("Telcompte") tel: String?,
        @Field("role") role: String?
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

    @DELETE("notify/delete.php")
    fun deleteAllNotifications(): Call<AnswerResponse>

    @DELETE("messages/deleteall.php")
    fun deleteAllConversation(): Call<AnswerResponse>

    @GET("client/liste.php")
    fun getList(): Call<ClientListeResponse>

    @GET("auth/liste.php")
    fun getAccounts(): Call<CompteListeResponse>

    @GET("messages/listofquestions.php")
    fun getquestions(): Call<QuestionListeResponse>

    @GET("commerciaux/liste.php")
    fun getAgents(): Call<AgentListeResponse>

    @GET("messages/history.php")
    fun getHistory(@Query("Telcompte") valeur: String): Call<QuestionListeResponse>

    @GET("messages/chathistory.php")
    fun getChatHistory(@Query("TelClient") valeur: String, @Query("Ticket") valeur2 : String)  : Call<QuestionListeResponse>

    @DELETE("notify/deleteanotif.php")
    fun deleteNotification(@Query("Idnotif") valeur: String)  : Call<AnswerResponse>

    @DELETE("commerciaux/supprimer.php")
    fun deleteAgent(@Query("nom") valeur: String,@Query("tel") valeur2: String)  : Call<AnswerResponse>


}