package com.glsservice.tg.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SendMessage {
    @SerializedName("QuestionClient")
    @Expose
    var QuestionClient : String? = null

    @SerializedName("TelClient")
    @Expose
    var telClient : String? = null

    @SerializedName("Ticket")
    @Expose
    var ticket : String? = null
}