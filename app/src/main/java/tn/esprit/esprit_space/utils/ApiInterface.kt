package tn.esprit.esprit_space.utils
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import tn.esprit.esprit_space.models.Absences
import tn.esprit.esprit_space.models.Classe
import tn.esprit.esprit_space.models.Notes
import tn.esprit.esprit_space.models.User

interface ApiInterface {
    @POST("user/login")
    fun login(@Body map : HashMap<String, String> ): Call<User>
    @POST("user/signup")
    fun signup(@Body map : HashMap<String, String> ): Call<User>

    @POST("notes/getUserNotes")
    fun getUserNotes(@Body map : HashMap<String, String> ): Call<Notes>

    @POST("absence/getUserAbs")
    fun getUserAbs(@Body map : HashMap<String, String> ): Call<Absences>

    @POST("use/getUserEmail")
    fun getUserEmail(@Body map : HashMap<String, String> ): Call<User>

    @POST("classe/getUserClasses")
    fun getUserClasses(@Body map : HashMap<String, String> ): Call<Classe>

    companion object {
        var BASE_URL = "http://10.0.2.2:5000/" //change with ur localhost
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}