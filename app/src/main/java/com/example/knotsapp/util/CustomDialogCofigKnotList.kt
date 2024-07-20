package com.example.knotsapp.util

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.knotsapp.R
import com.example.knotsapp.adapters.AdapterRecyclerViewLearnKnots
import com.example.knotsapp.adapters.AdapterRecyclerViewPracticeKnots
import com.example.knotsapp.controller.ControllerKnot

class CustomDialogCofigKnotList: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_config_knot_list,null)

        confingRecyclerView(view.findViewById(R.id.recyclerView))

        return AlertDialog
            .Builder(requireContext())
            .setView(view)
            .create()
    }


    fun confingRecyclerView(recyclerView: RecyclerView){

        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        var adapter:AdapterRecyclerViewPracticeKnots = AdapterRecyclerViewPracticeKnots( ControllerKnot(requireContext()).getAllKnot() )
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)


        Log.i("recycler", "confingRecyclerView: ")

    }

}

