package com.hyundaiht.android15test.foreground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat

class ForegroundStartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ForegroundStartReceiver", "onReceive")
        if (intent.action == ACTION_START_FOREGROUND) {
            Handler(Looper.getMainLooper()).postDelayed({
                Log.d("ForegroundStartReceiver", "startForegroundService")
                val serviceIntent = Intent(context, ForegroundTestService2::class.java)
                ContextCompat.startForegroundService(context, serviceIntent)
            }, 20000)
        }
    }

    companion object{
        const val ACTION_START_FOREGROUND = "com.hyundaiht.android15test.action.FOREGROUND_START"
    }
}