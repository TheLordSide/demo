package com.glsservice.tg.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JournauxTransfered(
    @SerializedName("Idjournal")
    @Expose
    val Idjournal : Int ? = null,

    @SerializedName("Telcommercial")
    @Expose
    val Telcommercial: String ? = null,

    @SerializedName("Date")
    @Expose
    val Date: String ? = null,

    @SerializedName("Qte")
    @Expose
    val Qte: String ? = null,
)
