package com.example.knotsapp.interfaces

import android.content.Context
import android.icu.text.Transliterator.Position
import com.example.knotsapp.util.Knot

interface AddSwitchKnotMethods {

    fun switchKnot(context: Context , name:String , activied:Boolean)

    fun updateList(position:Int , activied: Boolean)

    fun addHistoryList(knot: Knot)

}