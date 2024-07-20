package com.example.knotsapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.knotsapp.R
import com.example.knotsapp.util.Knot
import com.example.knotsapp.util.convertXMLToObject

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME:String = "database.db"
        val DATABASE_VERSION:Int = 10
    }

    var Knots:List<Knot> = convertXMLToObject( context.resources.getXml( R.xml.knots) ).convert()


    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE IF NOT EXISTS Knot (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                type TEXT,
                time INTEGER,
                score INTEGER,
                description TEXT,
                activated INTEGER DEFAULT 1 

            )
        """
        db.execSQL(createTable)


        Knots.forEach { knot ->
            val contentValues = ContentValues().apply {
                put("name",knot.name)
                put("type",knot.type)
                put("time",knot.time)
                put("score",knot.score)
                put("description",knot.description)
            }
            db.insert("Knot", null , contentValues)

        }
        Log.i("DATABASE", "OnCreate foi chamado")

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS Knot")
        onCreate(db)
    }

    fun getAllKnots(): List<Knot> {

        val cursor:Cursor = this.readableDatabase.rawQuery("SELECT * FROM Knot",null)

        val list = mutableListOf<Knot>()

        if ( cursor.moveToFirst() ){
            do {
                val knot:Knot = Knot()
                knot.name = cursor.getString( cursor.getColumnIndexOrThrow("name") )
                knot.type = cursor.getString( cursor.getColumnIndexOrThrow("type") )
                knot.time = cursor.getInt( cursor.getColumnIndexOrThrow("time") )
                knot.score = cursor.getInt( cursor.getColumnIndexOrThrow("score") )
                knot.description = cursor.getString( cursor.getColumnIndexOrThrow("description") )
                knot.activated = if(cursor.getInt( cursor.getColumnIndexOrThrow("activated")) == 0) false else true

                list.add(knot)

            } while (cursor.moveToNext())
        }

        return list
    }

    fun switchKnot(name:String , activated:Boolean){

        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            put("activated",activated)
        }

        db.update("Knot",contentValues,"name = ?", arrayOf(name))


    }
}