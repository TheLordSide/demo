package com.glsservice.tg.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

class JournalTransfertRequest {
    @SerializedName("Telcommercial")
    @Expose
    var numagent : String? = null

    @SerializedName("Qte")
    @Expose
    var Quantity : Int? = null

    @SerializedName("Date")
    @Expose
    var DateTransfert : Date? = null
}