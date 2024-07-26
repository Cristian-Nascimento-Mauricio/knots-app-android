package com.example.knotsapp.util

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.TextView
import com.example.knotsapp.R

class RunPractice(val context: Context, val list:ArrayList<Knot>, val textTime: TextView, val textKnotNameOrWarning: TextView) {

    var NowKnot:Knot = getRandomKnot()
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
                NowKnot = getRandomKnot()

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


    fun getRandomKnot():Knot{

        val selectKnot:List<Knot> = list.filter {
                item -> item.activated == true
        }

        if(selectKnot.size <= 0)
            return  list.get(0)

        return selectKnot.random()
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

    fun updateList(position:Int , activated:Boolean){
        list.get(position).activated = activated
    }


}