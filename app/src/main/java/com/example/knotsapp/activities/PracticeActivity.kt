package com.example.knotsapp.activities

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.knotsapp.R
import com.example.knotsapp.controller.ControllerKnot
import com.example.knotsapp.interfaces.AddSwitchKnotMethods
import com.example.knotsapp.util.CustomDialogCofigKnotList
import com.example.knotsapp.util.Knot
import com.example.knotsapp.util.RunPractice

class PracticeActivity : AppCompatActivity() , AddSwitchKnotMethods {
    val dialog = CustomDialogCofigKnotList()
    lateinit var runPractice:RunPractice
    lateinit var list:ArrayList<Knot>
    val historyList:MutableList<Knot> = mutableListOf()
    var isPaused:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practice)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        val textKnotNameOrWarning:TextView = findViewById(R.id.textKnotNameOrWarning)
        val textTime:TextView = findViewById(R.id.textTime)
        list = ArrayList( ControllerKnot(this).getAllKnot() )

        runPractice = RunPractice(this , list, textTime , textKnotNameOrWarning)

        runPractice.prepareTime(10_000)


    }

    fun btnConfig(view:View) {
        dialog.show(supportFragmentManager,"Okay")

    }

    fun btnClose(view: View){
        dialog.dismiss()

    }

    fun btnPrevious(view: View){

    }

    fun btnPaused(view: View){

        if(isPaused)
            view.findViewById<ImageButton>(R.id.btnPaused).setImageResource(R.drawable.img_play)
        else
            view.findViewById<ImageButton>(R.id.btnPaused).setImageResource(R.drawable.img_pause)

        isPaused = !isPaused
        /*
        if(::runPractice.isInitialized)
            runPractice.pauseTime(isPaused)

         */
    }

    fun btnNext(view: View){

    }

    override fun onBackPressed() {
        super.onBackPressed()
        runPractice.clearAudio()

    }

    override fun switchKnot(context: Context , name:String , activited:Boolean) {
        ControllerKnot(context).switchKnot(name, activited )

    }

    override fun updateList(position: Int, activied: Boolean) {
        if(::runPractice.isInitialized)
            runPractice.updateList(position,activied)
    }

    override fun addHistoryList(knot: Knot) {
        historyList.add(knot)
    }


}