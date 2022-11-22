package tn.esprit.esprit_space.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.esprit_space.IS_REMEMBRED
import tn.esprit.esprit_space.MainActivity
import tn.esprit.esprit_space.PREF_NAME
import tn.esprit.esprit_space.R


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (mSharedPref.getBoolean(IS_REMEMBRED, true))
        {
            Handler(Looper.getMainLooper()).postDelayed({
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }, 3000)
        }
        else
        {
            Handler(Looper.getMainLooper()).postDelayed({
                val mainIntent = Intent(this, Accueil::class.java)
                startActivity(mainIntent)
                finish()
            }, 3000)
        }
    }
}