package com.example.sailsal2

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_second.view.*

class SecondFragment : Fragment() {


    val args: SecondFragmentArgs by navArgs()
    var errorCode = 0
    var myData: String = ""
    var baseYear_text = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //var basicPay: String = "0"
        //var basicPay: String = "0"
        //var basicPay: String = "0"
        //var basicPay: String = "0"
        //var basicPay: String = "0"


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        view.button_next.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation_to_thirdFragment)
        }

        view.button_next.setOnClickListener {
            myData =
                "${args.data},${view.basic_text.text},${view.grade_text.text},${view.da_text.text},${view.mgb_text.text},${view.perk_text.text}"
            println(myData)


            // Generating error codes for all input field of second fragment
            if (view.basic_text.text.toString().isEmpty()) {
                errorCode = 1
            } else if (view.grade_text.text.toString().isEmpty()) {
                errorCode = 2
            } else if (view.da_text.text.toString().isEmpty()) {
                errorCode = 3
            } else if (view.mgb_text.text.toString().isEmpty()) {
                errorCode = 4
            } else if (view.perk_text.text.toString().isEmpty()) {
                errorCode = 5
            } else if ((view.grade_text.text.toString().toInt()) > 11) {
                errorCode = 6
            }else {
                errorCode = 0
            }


            println(errorCode)

            if (errorCode < 1) {
                val action = SecondFragmentDirections.navigationToThirdFragment(myData)
                Navigation.findNavController(view).navigate(action)
            } else {
                Toast.makeText(
                    requireActivity(),
                    "$errorCode",
                    Toast.LENGTH_LONG
                ).show()

                alertdialog(view, errorCode)
            }


        }

        return view
    }

    private fun alertdialog(view: View, p: Int) {
        lateinit var msg: String
        when (p) {
            1 -> msg =
                "Please enter the Basic Pay as on a month before the Base Year."
            2 -> msg =
                "Please enter the Grade on a month before the Base Year."
            3 -> msg = "Please enter the dearness Allowance as on the date of wage Board."
            4 -> msg =
                "Please enter the Minimum Guaranteed Benefit as per the agreement. "
            5 -> msg =
                "Please enter the Perk as per the agreement."
            6 -> msg = "Please enter Grade between 1 to 11."
        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Alert").setMessage("$msg")
            .setPositiveButton("ok") { dialogInterface, it ->
                dialogInterface.cancel()
            }.show()
    }
}