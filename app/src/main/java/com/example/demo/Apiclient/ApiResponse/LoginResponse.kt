package tg.intaonline.intaonline.ApiClient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("tel")
    @Expose
    val tel: String? = null

    @SerializedName("role")
    @Expose
    val role: String? = null

    @SerializedName("success")
    @Expose
    val success: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null


}