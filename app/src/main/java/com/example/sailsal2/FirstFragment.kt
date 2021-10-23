package com.example.sailsal2


import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.util.*


class FirstFragment : Fragment(R.layout.fragment_first) {

    var errorCode = 0
    var dateStr = ""
    var curDate = ""
    var baseYear_month = 1
    var baseYear_year = 2017
    var promotion_month = 0
    var promotion_year = 0
    var increment_month = 0
    var increment_year = 0
    var three_yr_promo_month = 0
    var three_yr_promo_year = 0
    var arrearsF_month = 0
    var arrearsF_year = 0
    var arrearsT_month = 0
    var arrearsT_year = 0
    var curDate_year = 0
    var curDate_month = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        view.button_next.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation_to_secondFragment)
        }


        //val currrentDate: String

        // To set all the date values from corresponding date picker.


        view.promotion_fbtn.setOnClickListener { view ->
            val promotion = datePickerView(promotion_text)
        }

        view.increment_fbtn.setOnClickListener { view ->
            val increment = datePickerView(increment_text)
        }

        view.three_yr_promo_fbtn.setOnClickListener { view ->
            val three_yr_promo = datePickerView(three_yr_promo_text)
        }

        view.arrearsF_fbtn.setOnClickListener { view ->
            val arrearsF = datePickerView(arrearsF_text)
        }

        view.arrearsT_fbtn.setOnClickListener { view ->
            val arrearsT = datePickerView(arrearsT_text)
        }



        view.button_next.setOnClickListener {
            move_to_secondFragment(view)
            errorCode = 0
        }


        return (view)
    }

    // creating values for date pickers
    private fun datePickerView(x: TextInputEditText): String {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { view, selectYear, selectMonth, selectDay ->
                dateStr = "$selectYear/${selectMonth + 1}/$selectDay"
                x.setText(dateStr)
            },
            year,
            month,
            day
        ).show()
        curDate = "$year/${month + 1}/$day"
        curDate_year = year
        curDate_month = month + 1
        return dateStr
    }

    private fun move_to_secondFragment(view: View) {
        // Assigning individual year and month values to string variables after splitting them.
        /*val wage_board = view.wage_board_text.text.toString()
        val wage_board_date = wage_board.split("/")
        baseYear_year = wage_board_date[0].toInt()
        baseYear_month = wage_board_date[1].toInt()*/

        if (view.promotion_text.text.toString().isNotEmpty() ||
            view.promotion_text.text.toString().isNotBlank()
        ) {
            val promotion = view.promotion_text.text.toString()
            val promo = promotion.split("/")
            promotion_year = promo[0].toInt()
            promotion_month = promo[1].toInt()
        } else {
            promotion_year = (curDate_year + 1)
            promotion_month = curDate_month
        }



        if (view.increment_text.text.toString().isNotEmpty() ||
            view.increment_text.text.toString().isNotBlank()
        ) {
            val increment = view.increment_text.text.toString()
            val increm = increment.split("/")
            increment_year = increm[0].toInt()
            increment_month = increm[1].toInt()
        }


        if (view.three_yr_promo_text.text.toString().isNotEmpty() ||
            view.three_yr_promo_text.text.toString().isNotBlank()
        ) {
            val three_yr_promo = view.three_yr_promo_text.text.toString()
            val threeYrPromo = three_yr_promo.split("/")
            three_yr_promo_year = threeYrPromo[0].toInt()
            three_yr_promo_month = threeYrPromo[1].toInt()
        } else {
            three_yr_promo_year = (curDate_year + 1)
            three_yr_promo_month = promotion_month
        }



        if (view.arrearsF_text.text.toString().isNotEmpty() ||
            view.arrearsF_text.text.toString().isNotBlank()
        ) {
            val arrearsFrom = view.arrearsF_text.text.toString()
            val arrearsF = arrearsFrom.split("/")
            arrearsF_year = arrearsF[0].toInt()
            arrearsF_month = arrearsF[1].toInt()
        }


        if (view.arrearsT_text.text.toString().isNotEmpty() ||
            view.arrearsT_text.text.toString().isNotBlank()
        ) {
            val arrearsTo = view.arrearsT_text.text.toString()
            val arrearsT = arrearsTo.split("/")
            arrearsT_year = arrearsT[0].toInt()
            arrearsT_month = arrearsT[1].toInt()
        }


        // Creating a new string variable to send it thrugh safeargs
        val myData = "$baseYear_year,$baseYear_month,$promotion_year,$promotion_month," +
                "$increment_year,$increment_month,$three_yr_promo_year,$three_yr_promo_month," +
                "$arrearsF_year,$arrearsF_month,$arrearsT_year,$arrearsT_month,$curDate_year,$curDate_month"

        //val action = FirstFragmentDirections.navigationToSecondFragment(myData)
        //Navigation.findNavController(view).navigate(action)

        // To handle the 0 value coming into myData.
        val empty_field_handling = myData.split(",")
        var myEmptyData_index = mutableListOf<Int>()
        for (x in empty_field_handling.indices) {
            if (empty_field_handling[x] == "0") {
                myEmptyData_index.add(x)
            }
        }


        //To set the error codes for the input dates
        if (((promotion_year < baseYear_year) || (promotion_year > curDate_year)) &&
            (view.promotion_text.text.toString().isNotEmpty() ||
                    view.promotion_text.text.toString().isNotBlank()
                    ))
         {
            errorCode = 2
        } else if ((arrearsF_year < baseYear_year) || (arrearsF_year > curDate_year)) {
            errorCode = 4
        } else if ((arrearsT_year < baseYear_year) || (arrearsT_year > curDate_year) || (arrearsT_year < arrearsF_year)) {
            errorCode = 5
        }


        if (myEmptyData_index.isNotEmpty()) {
            errorCode = 1
        }


        if (errorCode < 1) {
            val action = FirstFragmentDirections.navigationToSecondFragment(myData)
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

    private fun alertdialog(view: View, p: Int) {
        lateinit var msg: String
        when (p) {
            1 -> msg =
                "Please enter all the input field along with Promotion and Three Year Promotion if applicable else leave empty."
            2 -> msg =
                "Promotion date must be after the Date of wage board and before the current date."
            3 -> msg = "The month of promotion and the month of three year promotion must be same."
            4 -> msg =
                "Arrears From date should be on or after the Wage Board date as well as before the Arrears To date. "
            5 -> msg =
                "Arrears To date should be after the Wage Board date as well as Arrears From date."
            6 -> msg =
                "Date of Three Year Promotion should be at a difference of 3 year with the Normal 4 year promotion."
        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Alert").setMessage("$msg")
            .setPositiveButton("ok") { dialogInterface, it ->
                dialogInterface.cancel()
            }.show()
    }


}


