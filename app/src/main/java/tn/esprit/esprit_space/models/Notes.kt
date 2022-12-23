package tn.esprit.esprit_space.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Notes (
    @SerializedName("_id") val id : String,
    @SerializedName("matiere") val matiere : String,
    @SerializedName("cc") var cc : String,
    @SerializedName("examen") var examen : String,
    @SerializedName("iduser") var iduser : String,
    @SerializedName("createdAt") val createdAt : Date,
    @SerializedName("updatedAt") val updatedAt : Date,
) : Serializable