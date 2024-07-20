package com.example.knotsapp.util

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.TextView
import com.example.knotsapp.R

class RunPractice(val context: Context, val list:ArrayList<Knot>, val textTime: TextView, val textKnotNameOrWarning: TextView) {

    var NowKnot:Knot = list.random()
    val refereeAudio = MediaPlayer.create(context, R.raw.referee)
    lateinit var knotNameAudio:MediaPlayer
    lateinit var countDownTimerUntieTime:CountDownTimer
    lateinit var countDownTimerPrepareTime:CountDownTimer
    lateinit var countDownTimerDoKnotTime:CountDownTimer


    fun untieTime( ){
        countDownTimerUntieTime = object : CountDownTimer(15_000, 1_000){
            override fun onTick(p0: Long) {
                textTime.text = "" + p0.toInt() / 1000
                textKnotNameOrWarning.text = "Desfaça o nó"
            }

            override fun onFinish() {
                NowKnot = list.random()
                prepareTime()
            }

        }.start()

    }

    fun prepareTime(time: Long = 5_000){

        playNameKnot(NowKnot.name.toString())

        countDownTimerPrepareTime = object : CountDownTimer(time,1_000){
            override fun onTick(p0: Long) {
                textTime.text = "" + p0.toInt() / 1000
                textKnotNameOrWarning.text = NowKnot.name.toString()

            }

            override fun onFinish() {
                playReferee()
                NowKnot.time?.let { doKnotTime(it.toLong()) }
            }

        }.start()

    }

    fun doKnotTime(time:Long){
        countDownTimerDoKnotTime = object : CountDownTimer(time * 1_000, 1_000){
            override fun onTick(p0: Long) {
                textTime.text = ""

            }

            override fun onFinish() {
                playReferee()
                untieTime()
            }

        }.start()
    }

    private fun playReferee(){
        if(!refereeAudio.isPlaying())
            refereeAudio.start()

    }

    private fun playNameKnot(name:String){

        when(name){
            "Nó direito" -> knotNameAudio = MediaPlayer.create(context,R.raw.no_direito)
            "Pescador duplo" -> knotNameAudio = MediaPlayer.create(context,R.raw.pescador_duplo)
            "Escota dupla" -> knotNameAudio = MediaPlayer.create(context,R.raw.escota_dupla)
            "Aselha em 8" -> knotNameAudio = MediaPlayer.create(context,R.raw.aselha_em_8)
            "Aselha em 8 dupla" -> knotNameAudio =  MediaPlayer.create(context,R.raw.aselha_em_8_dupla)
            else -> knotNameAudio = MediaPlayer.create(context,R.raw.referee)
        }

        if(!knotNameAudio.isPlaying)
            knotNameAudio.start()
    }

    fun clearAudio(){
        refereeAudio.reset()
        knotNameAudio.reset()
        if (::countDownTimerUntieTime.isInitialized)
            countDownTimerUntieTime.cancel()

        if (::countDownTimerDoKnotTime.isInitialized)
            countDownTimerDoKnotTime.cancel()

        if (::countDownTimerPrepareTime.isInitialized)
            countDownTimerPrepareTime.cancel()

    }


}