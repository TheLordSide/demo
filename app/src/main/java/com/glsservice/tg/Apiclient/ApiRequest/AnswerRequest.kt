package com.glsservice.tg.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AnswerRequest {
    @SerializedName("ReponseAdmin")
    @Expose
    var reponse : String? = null

    @SerializedName("Ticket")
    @Expose
    var ticket : String? = null

    @SerializedName("TelClient")
    @Expose
    var telClient : String? = null

}