package com.glsservice.tg.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tg.intaonline.intaonline.ApiClient.ApiResponse.NotifyList

data class NotifyResponse(

    @SerializedName("liste")
    @Expose
    val liste: ArrayList<NotifyList>,

    @SerializedName("success")
    @Expose
    val success: String ? = null,

    @SerializedName("total")
    @Expose
    val total: Int ? = null
)