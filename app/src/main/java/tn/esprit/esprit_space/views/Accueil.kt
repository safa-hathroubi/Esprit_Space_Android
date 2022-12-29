package tn.esprit.esprit_space.views

//import androidx.appcompat.app.ActionBar


//import android.R
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_accueil.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_absences.*
import kotlinx.android.synthetic.main.fragment_evaluation.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.home_content.toolbar
import kotlinx.android.synthetic.main.nav_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.esprit_space.*
import tn.esprit.esprit_space.models.User
import tn.esprit.esprit_space.utils.ApiInterface


class Accueil : AppCompatActivity() {
    private lateinit var mSharedPref: SharedPreferences

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        setSupportActionBar(toolbar)


        val navView : NavigationView = findViewById(R.id.nav_view)



        val homeFragmentInstance = HomeFragment.newInstance("param","param")
        val messagesFragmentInstance = MessagesFragment.newInstance("param","param")
        val aboutFragmentInstance = AboutFragment.newInstance("param","param")
        val absencesFragmentInstance = AbsencesFragment.newInstance("param","param")
        val evaluationFragmentInstance = EvaluationFragment()
        val settingsFragmentInstance = SettingsFragment.newInstance("param","param")
        val transportFragmentInstance = TransportFragment.newInstance("param","param")







        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Toast.makeText(this@Accueil, "Welcome "+ mSharedPref.getString(LOGIN, "").toString(), Toast.LENGTH_SHORT).show()

        drawerLayout = findViewById(R.id.drawer_layout)


        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.frameLayout,HomeFragment()).commit()

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){

                R.id.nav_home -> replaceFragment(homeFragmentInstance, it.title.toString())
                R.id.nav_message -> replaceFragment(messagesFragmentInstance, it.title.toString())



                R.id.nav_sync -> replaceFragment(evaluationFragmentInstance, it.title.toString())
                R.id.nav_trash -> replaceFragment(absencesFragmentInstance, it.title.toString())
                R.id.nav_share -> {
                    val Intent= Intent(this,MapsActivity::class.java)
                    startActivity(Intent)}

                R.id.nav_login -> replaceFragment(settingsFragmentInstance, it.title.toString())
                R.id.nav_rate_us -> replaceFragment(aboutFragmentInstance, it.title.toString())
                R.id.nav_logout -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.logoutTitle))
                    builder.setMessage(R.string.logoutMessage)
                    builder.setPositiveButton("Yes"){ dialogInterface, which ->
                        getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().clear().apply()
                        finish()
                    }
                    builder.setNegativeButton("No"){dialogInterface, which ->
                        dialogInterface.dismiss()
                    }
                    builder.create().show()
                }
            }

            true


        }
/*
        logout_txtview.setOnClickListener { val intent = Intent(this@Accueil, MainActivity::class.java)
            startActivity(intent)
            finish() }*/


        //refresh(this@Accueil)        --------APP KEEPS STOPPING
        //requireContext().
        //mSharedPref = getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);   ---DUPLICATE


    }




    fun extractNameFromEmail(email1 : String): String {
        email1.substringBefore("@")
        email1.replace(".", " ")
        return email1
    }


    /*   APP  KEEPS STOPPING SO IM TAKING THIS OUT
        fun refresh(context: Context?)
    {
        context?.let {
            val fragementManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragementManager?.let {
                val currentFragement = fragementManager.findFragmentById(R.id.frameLayout)
                currentFragement?.let {
                    val fragmentTransaction = fragementManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach(it)
                    fragmentTransaction.commit()
                }
            }
        }
    }*/







    /*private fun replaceFragment(fragment: Fragment, title: String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }*/


    private fun replaceFragment(fragment: Fragment, title: String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){

            return true
        }

        return super.onOptionsItemSelected(item)
    }




}
















