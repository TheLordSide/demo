package com.example.demo.Apiclient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

class ClientRequest {
    @SerializedName("Nomclient")
    @Expose
    var nomClient: String? = null

    @SerializedName("Prenomclient")
    @Expose
    var prenomlient: String? = null

    @SerializedName("Sexeclient")
    @Expose
    var sexClient: String? = null

    @SerializedName("Naissanceclient")
    @Expose
    var dateClient: Date? = null

    @SerializedName("Fachatclient")
    @Expose
    var fachatclient: String? = null

    @SerializedName("Villeclient")
    @Expose
    var villeclient: String? = null

    @SerializedName("Quartierclient")
    @Expose
    var quartierclient: String? = null

    @SerializedName("Paysclient")
    @Expose
    var paysclient: String? = null

    @SerializedName("Telcompte")
    @Expose
    var telcompte: String? = null

}