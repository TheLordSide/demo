package com.glsservice.tg.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionListe(
    @SerializedName("Iddiscussion  ")
    @Expose
    val Iddiscussion  : Int ? = null,

    @SerializedName("Ticket")
    @Expose
    val Ticket: String ? = null,

    @SerializedName("QuestionClient")
    @Expose
    val QuestionClient: String ? = null,

    @SerializedName("ReponseAdmin")
    @Expose
    val ReponseAdmin: String ? = null,

    @SerializedName("TelClient")
    @Expose
    val TelClient: String ? = null,

    @SerializedName("Datequestion")
    @Expose
    val Datequestion: String ? = null,

    @SerializedName("Datereponse")
    @Expose
    val Datereponse: String ? = null,

    @SerializedName("Statudiscussion")
    @Expose
    val Statudiscussion: String ? = null,
)