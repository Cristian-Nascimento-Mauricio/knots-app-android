package com.example.knotsapp.util

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import com.example.knotsapp.R
import com.example.knotsapp.activities.PracticeActivity

class RunPractice(val context: Context, val list:ArrayList<Knot>, val textTime: TextView, val textKnotNameOrWarning: TextView) {

    var NowKnot:Knot = getRandomKnot()
    val refereeAudio = MediaPlayer.create(context, R.raw.referee)
    lateinit var knotNameAudio:MediaPlayer
    lateinit var countDownTimerUntieTime:CountDownTimerModified
    lateinit var countDownTimerPrepareTime:CountDownTimerModified
    lateinit var countDownTimerDoKnotTime:CountDownTimerModified

    fun untieTime( ){


        countDownTimerUntieTime = object : CountDownTimerModified(15_000, 1_000){
            override fun onTick(p0: Long) {
                textTime.text = "" + p0.toInt() / 1000
                textKnotNameOrWarning.text = "Desfaça o nó"
            }

            override fun onFinish() {
                super.onFinish()

                NowKnot = getRandomKnot()
                prepareTime()

            }

        }.startTime()

    }

    fun prepareTime(time: Long = 5_000){

        playNameKnot(NowKnot.name.toString())
        PracticeActivity().addHistoryList(NowKnot)

        countDownTimerPrepareTime = object : CountDownTimerModified(time,1_000){
            override fun onTick(p0: Long) {
                textTime.text = "" + p0.toInt() / 1000
                textKnotNameOrWarning.text = NowKnot.name.toString()

            }

            override fun onFinish() {
                super.onFinish()

                playReferee()
                NowKnot.time?.let { doKnotTime(it.toLong()) }
            }

        }.startTime()

    }

    fun doKnotTime(time:Long){
        countDownTimerDoKnotTime = object : CountDownTimerModified(time * 1_000, 1_000){
            override fun onTick(p0: Long) {
                textTime.text = ""

            }

            override fun onFinish() {
                super.onFinish()

                playReferee()
                untieTime()
            }

        }.startTime()
    }

    fun pauseTime(isPaused:Boolean){
        if(::countDownTimerUntieTime.isInitialized) {


        }
        if(::countDownTimerPrepareTime.isInitialized) {
            Log.i("test", "prepare: ${countDownTimerPrepareTime.isRunning}")

        }
        if(::countDownTimerDoKnotTime.isInitialized){
            Log.i("test", "do knot: ${countDownTimerDoKnotTime.isRunning}")

        }
        fun function(){

        }
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