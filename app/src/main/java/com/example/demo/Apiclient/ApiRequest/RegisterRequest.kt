package tg.intaonline.intaonline.ApiClient.ApiRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterRequest {
    @SerializedName("Telcompte")
    @Expose
    var phone : String? = null

    @SerializedName("Mdpcompte")
    @Expose
    var password : String? = null

}