package com.example.demo.Apiclient.ApiResponse

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

    )


