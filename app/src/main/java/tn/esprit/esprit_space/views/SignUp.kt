package tn.esprit.esprit_space.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import tn.esprit.esprit_space.R
import tn.esprit.esprit_space.utils.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast
import tn.esprit.esprit_space.MainActivity
import tn.esprit.esprit_space.models.User
import tn.esprit.esprit_space.views.Accueil
import tn.esprit.esprit_space.views.SignUp
class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        SignUpButton.setOnClickListener {
            Register()
        }
    }
    private fun validate(): Boolean {
        textInputLayoutEmailSignUp.error = null
        textInputLayoutPasswordSignUp.error = null

        if (textInputEditTextEmailSignUp.text!!.isEmpty()){
            textInputLayoutEmailSignUp.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (!textInputEditTextEmailSignUp.text!!.contains("@esprit.tn") ){
            textInputLayoutEmailSignUp.error = "Email Must be @esprit.tn"
            return false
        }
        if (textInputEditTextPasswordSignUp.text!!.isEmpty()){
            textInputLayoutPasswordSignUp.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }
    private fun Register() {
        if (validate()) {
            val apiInterface = ApiInterface.create()
            val map: HashMap<String, String> = HashMap()

            map["login"] = textInputEditTextEmailSignUp.text.toString()
            map["password"] = textInputEditTextPasswordSignUp.text.toString()
            apiInterface.signup(map).enqueue(object : Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {

                    val user = response.body()
                    if (user != null) {
                        val intent = Intent(this@SignUp, MainActivity::class.java)
                        startActivity(intent)
                    } else {

                        Toast.makeText(this@SignUp, "Email already exist !!", Toast.LENGTH_SHORT)
                            .show()

                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@SignUp, "Connexion error!", Toast.LENGTH_SHORT).show()
                }

            })

        }
    }
}