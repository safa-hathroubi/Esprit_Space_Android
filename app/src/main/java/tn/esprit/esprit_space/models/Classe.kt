package tn.esprit.esprit_space.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Classe(
    @SerializedName("_id") val id : String,
    @SerializedName("clas") val clas : String,
    @SerializedName("iduser") var iduser : String,

): Serializable
