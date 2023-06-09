package com.glsservice.tg.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotifyRequest {
    @SerializedName("Contentnotif")
    @Expose
    var  contentNotify: String? = null

    @SerializedName("auteur")
    @Expose
    var auteur : String? = null
}