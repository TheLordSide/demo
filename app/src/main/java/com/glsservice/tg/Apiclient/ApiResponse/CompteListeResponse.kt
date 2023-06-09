package com.glsservice.tg.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompteListeResponse(
    @SerializedName("liste")
    @Expose
    val liste: ArrayList<CompteList>,

    @SerializedName("success")
    @Expose
    val success: String ? = null,

    @SerializedName("total")
    @Expose
    val total: Int ? = null
)