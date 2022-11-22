package tn.esprit.esprit_space

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_evaluation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.esprit_space.models.Notes
import tn.esprit.esprit_space.models.User
import tn.esprit.esprit_space.utils.ApiInterface

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EvaluationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EvaluationFragment : Fragment(R.layout.fragment_evaluation) {
    private lateinit var mSharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_evaluation, container, false)
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
        GetNote(mSharedPref.getString(ID, "").toString())
        //resultat.setText("Hiii")
    }
    private fun GetNote(iduser : String) {
            val apiInterface = ApiInterface.create()
            val map: HashMap<String, String> = HashMap()
            map["iduser"] = iduser

            apiInterface.getUserNotes(map).enqueue(object : Callback<Notes> {

                override fun onResponse(call: Call<Notes>, response: Response<Notes>) {

                    val notes = response.body()
                    if (notes != null) {
                        Log.e("Notes : ", notes.toString())
                        resultat.setText("Matiére : "+notes.matiere+"\nCC : "+notes.cc+"\nExamen : "+notes.examen)
                    } else {

                        resultat.setText(notes.toString())
                    }
                }

                override fun onFailure(call: Call<Notes>, t: Throwable) {
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
         * @return A new instance of fragment EvaluationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EvaluationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}