package com.example.knotsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.knotsapp.R
import com.example.knotsapp.util.Knot

class AdapterRecyclerViewLearnKnots(private val list: List<Knot>): RecyclerView.Adapter<AdapterRecyclerViewLearnKnots.MyViewHolder>(){

     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

         //text
         var name:TextView = itemView.findViewById(R.id.name)
         var type:TextView = itemView.findViewById(R.id.type)
         var time:TextView = itemView.findViewById(R.id.time)
         var score:TextView = itemView.findViewById(R.id.score)
         var description:TextView = itemView.findViewById(R.id.description)

         //layout
         var information:LinearLayout = itemView.findViewById(R.id.information)
         var head:FrameLayout = itemView.findViewById(R.id.head)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_knots, parent , false)

        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list.get(position).name
        holder.type.text = list.get(position).type
        holder.time.text = list.get(position).time.toString()// Int -> String
        holder.score.text = list.get(position).score.toString()// Int -> String
        holder.description.text = list.get(position).description

        holder.head.setOnClickListener {

            if(holder.information.height <= 0)
                holder.information.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            else
                holder.information.layoutParams.height = 0

            holder.information.requestLayout()


        }

    }
}