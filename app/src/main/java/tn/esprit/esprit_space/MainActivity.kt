package tn.esprit.esprit_space

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import tn.esprit.esprit_space.utils.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast
import tn.esprit.esprit_space.models.User
import tn.esprit.esprit_space.views.Accueil
import tn.esprit.esprit_space.views.SignUp

const val PREF_NAME = "DATA_CV_PREF"
const val ID = "ID"
const val LOGIN = "LOGIN"
const val PASSWORD = "PASSWORD"
const val STATUS = "STATUS"
const val IS_REMEMBRED = "IS_REMEMBRED"

class MainActivity : AppCompatActivity() {

    private var txtEmail: TextInputEditText? = null
    private var txtLayoutEmail: TextInputLayout? = null

    private var btnSubmit : Button? = null

    lateinit var mSharedPref: SharedPreferences
    lateinit var cbRememberMe: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtEmail = findViewById(R.id.textInputEditTextEmail)
        txtLayoutEmail = findViewById(R.id.textInputLayoutEmail)

        btnSubmit = findViewById(R.id.loginButton)

        cbRememberMe = findViewById(R.id.cbRememberMe)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        if (mSharedPref.getBoolean(IS_REMEMBRED, false))
        {
            val homeIntent = Intent(this, Accueil::class.java)
            startActivity(intent)
            finish()
        }
        loginButton.setOnClickListener {
            doLogin()
        }
        buttonsignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
    private fun doLogin(){
        if (validate()){

            val apiInterface = ApiInterface.create()
            val map: HashMap<String, String> = HashMap()

            map["login"] = textInputEditTextEmail.text.toString()
            map["password"] = textInputEditTextPassword.text.toString()
            CoroutineScope(Dispatchers.IO).launch {

                apiInterface.login(map).enqueue(object : Callback<User>{

                    override fun onResponse(call: Call<User>, response:
                    Response<User>
                    ) {
                        val user = response.body()
                        Log.e("user : ", user.toString())
                        if(user != null)
                        {
                            Log.e("user : ", user.toString())
                            mSharedPref.edit().apply{
                                putString(ID,user.id)
                                putString(LOGIN, user.login)
                                putString(PASSWORD, user.password)
                                putString(STATUS, user.status)
                                putBoolean(IS_REMEMBRED, false)
                            }.apply()
                            val intent = Intent(this@MainActivity, Accueil::class.java)
                            startActivity(intent)
                            finish()
                        }

                        else
                        {
                            Toast.makeText(this@MainActivity,"Email or Password wrong !!",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.e("error",t.toString())
                        Toast.makeText(this@MainActivity,"Connexion error!",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
    private fun validate(): Boolean {
        txtLayoutEmail!!.error = null
        textInputLayoutPassword!!.error = null

        if (txtEmail?.text!!.isEmpty()) {
            txtLayoutEmail!!.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (textInputEditTextPassword.text!!.isEmpty()){
            textInputLayoutPassword!!.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail?.text!!).matches()) {
            txtLayoutEmail!!.error = getString(R.string.checkYourEmail)
            return false
        }

        return true
    }


}
