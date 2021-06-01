package com.thesis.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [parking_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class parking_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =inflater!!.inflate(R.layout.fragment_parking_fragment, container, false)
        view.findViewById<Button>(R.id.claw).setOnClickListener {
            activity?.let{
                val intent = Intent (it, parking_law::class.java)
                it.startActivity(intent)
            }
        }

        view.findViewById<Button>(R.id.chtm).setOnClickListener {
            activity?.let{
                val intent = Intent (it, Che_parking::class.java)
                it.startActivity(intent)
            }
        }
        view.findViewById<Button>(R.id.cn).setOnClickListener {
            activity?.let{
                val intent = Intent (it, ConParking::class.java)
                it.startActivity(intent)
            }
        }
        view.findViewById<Button>(R.id.hp).setOnClickListener {
            activity?.let{
                val intent = Intent (it, Heroes_park::class.java)
                it.startActivity(intent)
            }
        }
        view.findViewById<Button>(R.id.pdatabase).setOnClickListener {
            activity?.let{
                val intent = Intent (it, ParkinglotDB::class.java)
                it.startActivity(intent)
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment parking_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            parking_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}