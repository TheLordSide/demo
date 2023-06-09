package com.glsservice.tg.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClientUpdateRequest {
    @SerializedName("Telcompte")
    @Expose
    var phone : String? = null
}