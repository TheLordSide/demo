package com.glsservice.tg.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AgentListeResponse(

    @SerializedName("liste")
    @Expose
    val liste: ArrayList<AgentListe>,

    @SerializedName("success")
    @Expose
    val success: String ? = null,

    @SerializedName("total")
    @Expose
    val total: Int ? = null
)
