package tn.esprit.esprit_space


//import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.esprit_space.models.Classe
import tn.esprit.esprit_space.models.User
import tn.esprit.esprit_space.utils.ApiInterface


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */





class SettingsFragment : Fragment(R.layout.fragment_settings) {

    ///////////////////////////////////////////////////////////////////
    private var selectedImageUri: Uri? = null
    private var profilePic: ImageView? = null
    //lateinit var profilePic: ImageView

    ///////////////////////////////////////////////////////////////////

    /*
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/



    //logout_textview = findViewById<TextView>(R.id.logout_txtview)
    //logout_txtview.setOnClickListener(clickListener)

    //val textView = findViewById(R.id.text_view_id) as TextView

    private lateinit var mSharedPref: SharedPreferences




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        val profilePic = root.findViewById<ImageView>(R.id.profilePic)


        profilePic!!.setOnClickListener {
            openGallery()
        }
        // Inflate the layout for this fragment
        return root

    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startForResultOpenGallery.launch(intent)
    }

    private val startForResultOpenGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageUri = result.data!!.data
                profilePic?.setImageURI(selectedImageUri)
            }
        }
/*
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        refresh(context)
        mSharedPref = requireContext().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);
        GetEmail(mSharedPref.getString(ID, "").toString())
        //GetEmail("637bc4965a7ee89544b57387")
        //resultat.setText("Hiii")
    }

    private fun GetEmail(iduser : String) {
        val apiInterface = ApiInterface.create()
        val map: HashMap<String, String> = HashMap()
        map["iduser"] = iduser

        apiInterface.getUserEmail(map).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                val userr = response.body()
                if (userr != null) {
                    Log.e("Use : ", userr.toString())
                    usermailTextView.setText(userr.login)
                } else {

                    usermailTextView.setText(userr.toString())
                }
            }


            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Connexion error!", Toast.LENGTH_SHORT).show()
            }

        })

    }*/


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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        refresh(context)
        mSharedPref = requireContext().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);
        GetClasse(mSharedPref.getString(ID, "").toString())
        //GetEmail("637bc4965a7ee89544b57387")
        //resultat.setText("Hiii")
    }

    private fun GetClasse(iduser : String) {
        val apiInterface = ApiInterface.create()
        val map: HashMap<String, String> = HashMap()
        map["iduser"] = iduser

        apiInterface.getUserClasses(map).enqueue(object : Callback<Classe> {

            override fun onResponse(call: Call<Classe>, response: Response<Classe>) {

                val classe = response.body()
                if (classe != null) {
                    Log.e("Classe : ", classe.toString())
                    usermailTextView.setText(classe.clas)
                } else {

                    usermailTextView.setText(classe.toString())
                }
            }


            override fun onFailure(call: Call<Classe>, t: Throwable) {
                Toast.makeText(context, "Connexion error!", Toast.LENGTH_SHORT).show()
            }

        })

    }
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val clickListener = View.OnClickListener {view ->

            when (view.getId()) {
                R.id.textview -> firstFun()
            }

            logout_textview.setOnClickListener { val intent = Intent(activity, Accueil::class.java)
                startActivity(intent)
                finish()}
        }
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}