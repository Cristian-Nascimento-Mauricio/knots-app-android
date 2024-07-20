package com.example.knotsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.knotsapp.R
import com.example.knotsapp.activities.PracticeActivity
import com.example.knotsapp.interfaces.AddSwitchKnotMethods
import com.example.knotsapp.util.Knot

class AdapterRecyclerViewPracticeKnots(private val list: List<Knot>):
    RecyclerView.Adapter<AdapterRecyclerViewPracticeKnots.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var text:Switch = itemView.findViewById(R.id.switchKnot)

    }
    override fun onCreateViewHolder( parent: ViewGroup , viewType: Int ): MyViewHolder {


        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_switch_knot, parent , false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)  {
        holder.text.text = list.get(position).name.toString()
        holder.text.isChecked = list.get(position).activated
        holder.text.setOnClickListener{
            PracticeActivity().switchKnot(holder.itemView.context, list.get(position).name.toString() , holder.text.isChecked)

        }


    }

    override fun getItemCount(): Int {
        return 5
    }


}