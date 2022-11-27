package tn.esprit.esprit_space.views

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
//import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.home_content.*
import tn.esprit.esprit_space.*


class Accueil : AppCompatActivity() {
    private lateinit var mSharedPref: SharedPreferences

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        setSupportActionBar(toolbar)

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
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){

                R.id.nav_home -> replaceFragment(homeFragmentInstance, it.title.toString())
                R.id.nav_message -> replaceFragment(messagesFragmentInstance, it.title.toString())

                

                R.id.nav_sync -> replaceFragment(evaluationFragmentInstance, it.title.toString())
                R.id.nav_trash -> replaceFragment(absencesFragmentInstance, it.title.toString())
                R.id.nav_share -> replaceFragment(transportFragmentInstance, it.title.toString())
                R.id.nav_login -> replaceFragment(settingsFragmentInstance, it.title.toString())
                R.id.nav_rate_us -> replaceFragment(aboutFragmentInstance, it.title.toString())
            }

            true


        }

    }


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















