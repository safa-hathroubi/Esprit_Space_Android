package tn.esprit.esprit_space.models
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class User (
    @SerializedName("_id") val id : String,
    @SerializedName("login") val login : String,
    @SerializedName("password") val password : String,
    @SerializedName("status") val status  : String,
) :  Serializable