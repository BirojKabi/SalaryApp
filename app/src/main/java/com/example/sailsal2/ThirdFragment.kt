package com.example.sailsal2

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_third.*
import kotlinx.android.synthetic.main.fragment_third.view.*

class ThirdFragment : Fragment() {
    val args: ThirdFragmentArgs by navArgs()
    var dataToFourth: String = "0000"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val dataCompleteList = args.wholeData.split(",")

        var stagnantValue = 0


        //---------------------------------All input dat------------------------------------------
        val baseYearY = dataCompleteList[0].toInt()
        val baseYearM = dataCompleteList[1].toInt()
        var promotionY = dataCompleteList[2].toInt()
        val promotionM = dataCompleteList[3].toInt()
        //val incrementY = dataCompleteList[4].toInt()
        val incrementM = dataCompleteList[5].toInt()
        var promotion3Y = dataCompleteList[6].toInt()
        var promotion3M = dataCompleteList[7].toInt()
        val arrearStartY = dataCompleteList[8].toInt()
        val arrearStartM = dataCompleteList[9].toInt()
        val arrearEndY = dataCompleteList[10].toInt()
        val arrearEndM = dataCompleteList[11].toInt()
        val currrentDateY = dataCompleteList[12].toInt()
        val currrentDateM = dataCompleteList[13].toInt()
        val basicI = dataCompleteList[14].toInt()
        var grdno = dataCompleteList[15].toInt()
        val da = dataCompleteList[16].toDouble()
        val mgb = dataCompleteList[17].toDouble()
        val perk = dataCompleteList[18].toDouble()


        //--------------------------------pay slab structures-------------------------

        val slabdata = listOf<Int>(0,22150,23200,24110,25540,27500,30070,31230,32420,35480,42030,43950)
        val slabdata_new = listOf<Int>(0,67190, 70374, 73135, 77473, 83419, 91214, 94734, 98344, 107625, 127493, 133318)



        //------------------------------da values------------------------------
        val oldDaP = listOf<Double>(40.1, 40.1, 40.1, 38.5, 38.5, 38.5, 39.7, 39.7, 39.7, 43.1, 43.1, 43.1,
            44.8, 44.8, 44.8, 44.9, 44.9, 44.9, 45.5, 45.5, 45.5, 50.3, 50.3, 50.3,
            52.4, 52.4, 52.4, 54.0, 54.0, 54.0, 57.4, 57.4, 57.4, 60.8, 60.8, 60.8,
            64.1, 64.1, 64.1, 66.3, 66.3, 66.3, 65.8, 65.8, 65.8, 69.4, 69.4, 69.4,
            73.2, 73.2, 73.2, 72.6, 72.6, 72.6, 74.6, 74.6, 74.6, 78.1, 78.1, 78.1)

        val newDaP = listOf<Double>(0.0, 0.0, 0.0, -1.1, -1.1, -1.1, -0.2, -0.2, -0.2, 2.2, 2.2, 2.2, 3.4, 3.4, 3.4,
            3.6, 3.6, 3.6, 3.9, 3.9, 3.9,7.4, 7.4, 7.4, 8.9, 8.9, 8.9, 10.1, 10.1, 10.1,
            14.2, 14.2, 14.2, 14.8, 14.8, 14.8, 17.2, 17.2, 17.2, 18.8, 18.8, 18.8,
            18.4,18.4, 18.4, 20.9, 20.9, 20.9, 23.7, 23.7, 23.7, 23.2, 23.2, 23.2,
            25.1, 25.1, 25.1, 28.6, 28.6, 28.6)
//--------------------------calculation Starts from here-------------------------------------------------

        var hike = 0
        var hikeList = mutableListOf<Int>()     // It is for finding increment and promotion simultaneously
        var year = mutableListOf<Int>()
        var month = mutableListOf<Int>()
        var scaleCode = mutableListOf<Int>()
        var basicPay = mutableListOf<Int>(0)
        var newBasicPay = mutableListOf<Int>(0)

        //-----------------preparing month and year list--------------------------------------------------------------

        for(years in baseYearY..currrentDateY){
            if(years == baseYearY)
                for( months in baseYearM..12){
                    year.add(years)
                    month.add(months)

                }else if(years == currrentDateY)
                for( months in 1..currrentDateM){
                    year.add(years)
                    month.add(months)
                }
            else
                for( months in 1..12){
                    year.add(years)
                    month.add(months)
                }

        }

//----------------------finding promotion, 3yr promotion & increment calculation all at a time------------------

        //----------------------finding promotion, 3yr promotion & increment calculation all at a time------------------
        for (i in year.indices){
            scaleCode.add(0)        // to fix list index outofBound exception
            hikeList.add(0)         // // to fix list index outofBound exception
            if ((grdno != 11) && (year[i]==promotionY) && (month[i] == promotionM))
            {scaleCode[i] = grdno.inc()
                grdno = grdno.inc()
                promotionY = promotionY + 4
                if (promotionM == incrementM){
                    hike = hike + 2
                    hikeList[i] = hike
                }else{
                    hike = hike + 1
                    hikeList[i] = hike
                }}


            else if ((grdno != 11) && (year[i]==promotion3Y) && (month[i] == promotion3M))
            {scaleCode[i] = grdno.inc()
                grdno = grdno.inc()
                promotionY = promotionY + 4
                if (promotionM == incrementM){
                    hike = hike + 2
                    hikeList[i] = hike
                }else{
                    hike = hike + 1
                    hikeList[i] = hike
                }}

            else
            {if (i != 0)
            {scaleCode[i] = scaleCode[i-1]
                if (month[i] == incrementM){
                    hike = hike + 1
                    hikeList[i] = hike
                }else{hikeList[i] = hikeList[i-1]}}
            else
            {scaleCode[i] = grdno.toInt()
                if (month[i] == incrementM){
                    hike = hike + 1
                    hikeList[i] = hike
                }else{hikeList[i] = 0}}
            }
        }

        //-----------------------------finding list of old basic structure-----------------------------
        for (i in hikeList.indices){

            if (i != 0){
                var hikeDif = hikeList[i] - hikeList[i-1]

                when (hikeDif){
                    0 -> basicPay.add(basicPay[i-1])
                    1 -> basicPay.add((basicPay[i-1] * 1.03).toInt())
                    2 -> basicPay.add((basicPay[i-1] * 1.03 * 1.03).toInt())
                }
            }else
            {
                when (hikeList[0]){
                    0 -> basicPay[0] = basicI
                    1 -> basicPay[0] = (basicI * 1.03).toInt()
                    2 -> basicPay[0] = (basicI * 1.03 * 1.03).toInt()
                }
            }
        }

        //------------------------------------finding stagnancy for Old basics----------------------------

        for (i in hikeList.indices){
            if (i != 0){
                var hikeDif = hikeList[i] - hikeList[i-1]

                when (hikeDif){
                    0 -> basicPay[i] = (basicPay[i-1])
                    1 -> if(basicPay[i] >= slabdata[scaleCode[i]]){
                        basicPay[i] = basicPay[i-1].plus(slabdata[scaleCode[i]] * 0.03).toInt()
                        stagnantValue = 1}
                    2 -> if(basicPay[i] >= slabdata[scaleCode[i]]){
                        basicPay[i] = basicPay[i-1].plus(slabdata[scaleCode[i]] * 0.03 * 0.03).toInt()
                        stagnantValue = 1}
                }
            }else
            {
                when (hikeList[0]){
                    0 -> basicPay[i] = basicI
                    1 -> if(basicPay[i] >= slabdata[scaleCode[i]]){
                        basicPay[i] = basicI.plus(slabdata[scaleCode[i]] * 0.03).toInt()
                        stagnantValue = 1}
                    2 -> if(basicPay[i] >= slabdata[scaleCode[i]]){
                        basicPay[i] = basicI.plus(slabdata[scaleCode[i]] * 0.03 * 0.03).toInt()
                        stagnantValue = 1}
                }
            }
        }


        //-----------------------------------old DA list preparation-------------------------------------------------
        var oldDa = mutableListOf<Int>()
        for (i in basicPay.indices){
            oldDa.add((basicPay[i] * (oldDaP[i]/100)).toInt())
        }

        //-----------------------------------old Basic plus Da value-------------------------------------------------
        var oldTotal = mutableListOf<Int>()
        for (i in basicPay.indices){
            oldTotal.add((basicPay[i] + oldDa[i]).toInt())
        }


        //-----------------------------------New Basic calculation-------------------------------------------------
        val newBasicP = ((basicI!! + oldDa[0]) * 1.13).toInt()
        for (i in hikeList.indices){

            if (i != 0){
                var hikeDif = hikeList[i] - hikeList[i-1]
                when (hikeDif){
                    0 -> newBasicPay.add(newBasicPay[i-1])
                    1 -> newBasicPay.add((newBasicPay[i-1] * 1.03).toInt())
                    2 -> newBasicPay.add((newBasicPay[i-1] * 1.03 * 1.03).toInt())
                }
            }else
            {
                when (hikeList[0]){
                    0 -> newBasicPay[0] = newBasicP
                    1 -> newBasicPay[0] = (newBasicP * 1.03).toInt()
                    2 -> newBasicPay[0] = (newBasicP * 1.03 * 1.03).toInt()
                }
            }
        }


        //------------------------------------finding stagnancy for New basics----------------------------

        /*
        for (i in hikeList.indices){
            if (i != 0){
                var hikeDif = hikeList[i] - hikeList[i-1]

                when (hikeDif){
                    0 -> newBasicPay[i] = (newBasicPay[i-1])
                    1 -> if(newBasicPay[i] >= slabdata_new[scaleCode[i]]){
                        newBasicPay[i] = newBasicPay[i-1].plus(slabdata_new[scaleCode[i]] * 0.03).toInt()}
                    2 -> if(newBasicPay[i] >= slabdata_new[scaleCode[i]]){
                        newBasicPay[i] =newBasicPay[i-1].plus(slabdata_new[scaleCode[i]] * 0.03 * 0.03).toInt()}
                }
            }else
            {
                when (hikeList[0]){
                    0 -> newBasicPay[i] = newBasicP
                    1 -> if(newBasicPay[i] >= slabdata_new[scaleCode[i]]){
                        newBasicPay[i] = newBasicP.plus(slabdata_new[scaleCode[i]] * 0.03).toInt()}
                    2 -> if(newBasicPay[i] >= slabdata_new[scaleCode[i]]){
                        newBasicPay[i] = newBasicP.plus(slabdata_new[scaleCode[i]] * 0.03 * 0.03).toInt()}
                }
            }
        }

         */

        //-----------------------------------New DA calculation-------------------------------------------------
        var newDa = mutableListOf<Int>()
        for (i in basicPay.indices){
            newDa.add((newBasicPay[i] * (newDaP[i]/100)).toInt())
        }

        //-----------------------------------old Basic plus Da value-------------------------------------------------
        var newTotal = mutableListOf<Int>()
        for (i in basicPay.indices){
            newTotal.add((newBasicPay[i] + newDa[i]).toInt())
        }

        //-----------------------------------Difference calculation-------------------------------------------------
        var difference = mutableListOf<Int>()
        for (i in basicPay.indices){
            difference.add((newTotal[i] - oldTotal[i]).toInt())
        }

        //-----------------------------------Arrears calculation-------------------------------------------------
        var arrears = 0
        for (i in difference.indices){
            if ((month[i] >= arrearStartM) && (year[i] >= arrearStartY) && (month[i] <= arrearEndM) && (year[i] <= arrearEndY)){
                arrears = arrears + difference[i]
            }
        }

        val totalWthPerk = newTotal.last() + perk.toInt()




        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third, container, false)
        var recycler_view = view.recycler_view
        recycler_view.layoutManager = LinearLayoutManager(context)

        view.to_fourth.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.navigation_to_fourthFragment)}

        dataToFourth = "${basicPay.last()},${oldDa.last()},${oldTotal.last()},${newBasicPay.last()},${newDa.last()}," +
                "${newTotal.last()},${(newTotal.last() + (newBasicPay.last()*(perk/100))).toInt()},${arrears},$currrentDateY,$currrentDateM"



        if (stagnantValue == 1) {
            alertdialog(view)
        }

        var salary = mutableListOf<MyData>()


        for(m in 1..year.size){
            var mySalary = MyData(
                month[m-1],
                year[m-1],
                scaleCode[m-1],
                basicPay[m-1],
                oldDa[m-1],
                oldTotal[m-1],
                newBasicPay[m-1],
                newDa[m-1],
                newTotal[m-1],
                difference[m-1]
            )
             salary.add(mySalary)

        }

        recycler_view.adapter = MyAdapter(salary)
        println("dataToFourth")

        view.to_fourth.setOnClickListener {


            // Setting an action for sending myData from first fragment to second fragment.


            val action1 = ThirdFragmentDirections.navigationToFourthFragment(dataToFourth)
            Navigation.findNavController(view).navigate(action1)


        }

        return view
    }

    private fun alertdialog(view: View) {

        var msg = "Your Basic structure is stagnant. Since new basic structure is not out yet, Open ended Basic pay will be displayed here"
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Alert").setMessage("$msg")
            .setPositiveButton("ok") { dialogInterface, it ->
                dialogInterface.cancel()
            }.show()
    }



}