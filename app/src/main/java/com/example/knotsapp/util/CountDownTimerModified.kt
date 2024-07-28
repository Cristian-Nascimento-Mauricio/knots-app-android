package com.example.knotsapp.util

import android.os.CountDownTimer


open class CountDownTimerModified(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

    var isRunning:Boolean = false


    override fun onTick(p0: Long) {

    }

    override fun onFinish() {
        isRunning = false

    }


    fun startTime():CountDownTimerModified{
        isRunning = true
        super.start()
        return this
    }

}