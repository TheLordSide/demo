package com.glsservice.tg.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompteList(
    @SerializedName("Idcompte ")
    @Expose
    var idcompte: String? = null,

    @SerializedName("Telcompte")
    @Expose
    var Telcompte: String? = null,

    @SerializedName("Mdpcompte")
    @Expose
    var Mdpcompte: String? = null,

    @SerializedName("role")
    @Expose
    var role: String? = null,

    @SerializedName("created_on")
    @Expose
    var created_on: String? = null,

    )



