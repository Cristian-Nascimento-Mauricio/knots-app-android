package com.example.knotsapp.activities

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.knotsapp.R
import com.example.knotsapp.controller.ControllerKnot
import com.example.knotsapp.database.DatabaseHelper
import com.example.knotsapp.util.convertXMLToObject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    fun btnPracticeClick(view: View) {

        Toast.makeText(this, "Indo para area de práticar", Toast.LENGTH_LONG).show()
        val intent = Intent(this,PracticeActivity::class.java)
        startActivity(intent)


    }

    fun btnLearnClick(view: View){

        Toast.makeText(this, "Indo para area de estudos", Toast.LENGTH_LONG).show()
        val intent = Intent(this, LearnActivity::class.java)
        startActivity(intent)

    }

}