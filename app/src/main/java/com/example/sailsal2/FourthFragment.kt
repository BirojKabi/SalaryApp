package com.example.sailsal2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_fourth.view.*


class FourthFragment : Fragment() {
    val args: FourthFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataToShow = args.singleData.split(",")

        val view =  inflater.inflate(R.layout.fragment_fourth, container, false)

        var growth = (dataToShow[5].toInt() - dataToShow[2].toInt())

        view.basic_old.text = dataToShow[0]
        view.da_old.text = dataToShow[1]
        view.total_old.text = dataToShow[2]
        view.basic_new.text = dataToShow[3]
        view.da_new.text = dataToShow[4]
        view.total_new.text = dataToShow[5]
        view.differences.text = growth.toString()
        view.perk.text = dataToShow[6]
        view.arrears.text = dataToShow[7]
        view.curDate.text = "As On ${dataToShow[9]}/${dataToShow[8]}"


        return view
    }


}