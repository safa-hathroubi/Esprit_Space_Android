package tn.esprit.esprit_space

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_absences.*
import kotlinx.android.synthetic.main.fragment_evaluation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.esprit_space.models.Absences
import tn.esprit.esprit_space.models.Notes
import tn.esprit.esprit_space.models.User
import tn.esprit.esprit_space.utils.ApiInterface



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AbsencesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AbsencesFragment : Fragment(R.layout.fragment_absences) {
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

    private lateinit var mSharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_absences, container, false)
        return rootView
    }
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
        GetAbs(mSharedPref.getString(ID, "").toString())
        //resultat.setText("Hiii")
    }

    private fun GetAbs(iduser : String) {
        val apiInterface = ApiInterface.create()
        val map: HashMap<String, String> = HashMap()
        map["iduser"] = iduser

        apiInterface.getUserAbs(map).enqueue(object : Callback<Absences> {

            override fun onResponse(call: Call<Absences>, response: Response<Absences>) {

                val abss = response.body()
                if (abss != null) {
                    Log.e("Notes : ", abss.toString())
                    ab_row_subj1.setText(abss.matiere)
                    ab_row_date1.setText(abss.date)
                    ab_row_justif1.setText(abss.justificatif)
                } else {

                    ab_row_subj1.setText(abss.toString())
                    ab_row_date1.setText(abss.toString())
                    ab_row_justif1.setText(abss.toString())
                }
            }

            override fun onFailure(call: Call<Absences>, t: Throwable) {
                Toast.makeText(context, "Connexion error!", Toast.LENGTH_SHORT).show()
            }

        })

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AbsencesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AbsencesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}