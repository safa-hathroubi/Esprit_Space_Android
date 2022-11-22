package tn.esprit.esprit_space.utils
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import tn.esprit.esprit_space.models.Notes
import tn.esprit.esprit_space.models.User

interface ApiInterface {
    @POST("user/login")
    fun login(@Body map : HashMap<String, String> ): Call<User>
    @POST("user/signup")
    fun signup(@Body map : HashMap<String, String> ): Call<User>

    @POST("notes/getUserNotes")
    fun getUserNotes(@Body map : HashMap<String, String> ): Call<Notes>

    companion object {
        var BASE_URL = "http://192.168.1.102:5000/" //change with ur localhost
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}