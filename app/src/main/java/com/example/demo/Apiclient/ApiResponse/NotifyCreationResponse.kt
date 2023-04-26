package com.example.demo.Apiclient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotifyCreationResponse {

    @SerializedName("success")
    @Expose
    val success: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null
}