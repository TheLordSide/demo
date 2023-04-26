package tg.intaonline.intaonline.ApiClient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotifyList(
    @SerializedName("Idnotif ")
    @Expose
    val IdNotification : Int ? = null,

    @SerializedName("Contentnotif")
    @Expose
    val ContentNotification: String ? = null,

    @SerializedName("Datenotif")
    @Expose
    val DateNotification: String ? = null,

    @SerializedName("auteur")
    @Expose
    val Auteur: String ? = null,
)