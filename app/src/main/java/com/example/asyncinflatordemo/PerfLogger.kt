package com.example.asyncinflatordemo

import android.os.SystemClock
import android.util.Log

class PerfLogger {

    companion object {
        infix fun log(message: String) {
            Log.d("xperf", "${Thread.currentThread().name} -- $message")
        }

        inline fun <T> measured(tag: String = "xperf", message: String = "", bloc: () -> T): T {
            val time = SystemClock.uptimeMillis()
            val a = bloc()
            Log.d(
                tag,
                "${SystemClock.uptimeMillis() - time} -- ${Thread.currentThread().stackTrace[2].className} ${Thread.currentThread().stackTrace[2].methodName} $message ${Thread.currentThread().name}"
            )
            return a
        }

        inline fun <T> measuredWithCaller(
            tag: String = "xperf",
            message: String = "",
            bloc: () -> T
        ): T {
            val time = SystemClock.uptimeMillis()
            val a = bloc()
            Log.d(
                tag,
                " ${SystemClock.uptimeMillis() - time} -- ${Thread.currentThread().stackTrace[3].className} ${Thread.currentThread().stackTrace[3].methodName} ${Thread.currentThread().stackTrace[2].className} ${Thread.currentThread().stackTrace[2].methodName} $message ${Thread.currentThread().name}"
            )
            return a
        }
    }
}