package tg.intaonline.intaonline.ApiClient.ApiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("success")
    @Expose
    val success: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null


}