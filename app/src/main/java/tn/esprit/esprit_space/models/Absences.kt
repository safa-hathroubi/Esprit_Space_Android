package tn.esprit.esprit_space.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Absences(
    @SerializedName("_id") val id : String,
    @SerializedName("matiere") val matiere : String,
    @SerializedName("justificatif") var justificatif : String,
    @SerializedName("iduser") var iduser : String,
    @SerializedName("date") var date : String,

    //@SerializedName("createdAt") val createdAt : Date,
    //@SerializedName("updatedAt") val updatedAt : Date,
):Serializable
