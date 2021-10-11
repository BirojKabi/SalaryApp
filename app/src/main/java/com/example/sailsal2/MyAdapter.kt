package com.example.sailsal2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class MyAdapter(var year: List<MyData>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // "apply" helps us to write repeated codes
        holder.itemView.apply{
            years.text = "1/${year[position].month.toString()}/${year[position].years.toString()}"
            grade.text = "S${ year[position].grade.toString() }"
            //month.text = year[position].month.toString()
            basic.text = year[position].old_basic.toString()
            oldDa.text = year[position].old_da.toString()
            oldTotal.text = year[position].old_total.toString()
            newBasic.text = year[position].new_basic.toString()
            newDa.text = year[position].new_da.toString()
            newTotal.text = year[position].new_total.toString()

        }

    }

    override fun getItemCount(): Int {
        return year.size
    }


}

