package com.example.knotsapp.util

import android.content.res.XmlResourceParser
import android.util.Log
import org.xmlpull.v1.XmlPullParser

class convertXMLToObject(val parser: XmlResourceParser) {

    var list = arrayListOf<Knot>()


    fun convert():ArrayList<Knot> {

        while (parser.next() != XmlPullParser.END_DOCUMENT)
            if(parser.eventType == XmlPullParser.START_TAG && parser.name == "knot")
                list.add(analising())

        return list
    }

    private fun analising():Knot {
        val knot = Knot()

        while (parser.next() != XmlPullParser.END_TAG && parser.name != "knot")
            if(parser.eventType == XmlPullParser.START_TAG)
                when(parser.name){
                    "name"  -> knot.name = parser.nextText()
                    "type"  -> knot.type = parser.nextText()
                    "time"  -> knot.time = parser.nextText().toInt()
                    "score" -> knot.score = parser.nextText().toInt()
                    "description" -> knot.description = parser.nextText()

                }


        return knot
    }

    fun print(knot: Knot) {
        Log.d("XML", "" +
                knot.name + "\n" +
                knot.type + "\n" +
                knot.time + "\n" +
                knot.score + "\n" +
                knot.description + "\n"

        )
    }

}