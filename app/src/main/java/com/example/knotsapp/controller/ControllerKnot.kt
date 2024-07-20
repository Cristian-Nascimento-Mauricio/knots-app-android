package com.example.knotsapp.controller

import android.content.Context
import com.example.knotsapp.database.DatabaseHelper
import com.example.knotsapp.util.Knot

class ControllerKnot(context: Context) {

    private val databaseHelper = DatabaseHelper(context)

    fun getAllKnot():List<Knot>{
        return  databaseHelper.getAllKnots()

    }

    fun switchKnot(name:String , activited:Boolean){
        databaseHelper.switchKnot(name , activited)
    }
}