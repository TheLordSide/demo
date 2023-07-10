package com.glsservice.tg.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateAgentRequest {

    @SerializedName("nom")
    @Expose
    var nom : String? = null

    @SerializedName("ville")
    @Expose
    var ville : String? = null

    @SerializedName("quartier")
    @Expose
    var quartier : String? = null

    @SerializedName("tel")
    @Expose
    var tel : String? = null
}