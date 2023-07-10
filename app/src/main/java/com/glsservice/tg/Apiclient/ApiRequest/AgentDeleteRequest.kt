package com.glsservice.tg.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AgentDeleteRequest {


    @SerializedName("nom")
    @Expose
    var nom : String? = null


    @SerializedName("tel")
    @Expose
    var tel : String? = null
}