package com.glsservice.tg.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CompteUpdateResponse
{

    @SerializedName("success")
    @Expose
    val success: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null

}