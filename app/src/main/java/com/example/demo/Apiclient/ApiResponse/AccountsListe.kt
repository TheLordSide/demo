package com.example.demo.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountsListe(
    @SerializedName("Idcompte ")
    @Expose
    val IdAccount : Int ? = null,

    @SerializedName("Telcompte")
    @Expose
    val TelAccount: String ? = null,

    @SerializedName("Mdpcompte")
    @Expose
    val MdpAccount: String ? = null,

    @SerializedName("role")
    @Expose
    val role: String ? = null,
)