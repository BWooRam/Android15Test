package com.hyundaiht.android15test.foreground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat

class ForegroundBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ForegroundBootReceiver", "onReceive")
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val serviceIntent = Intent(context, ForegroundTestService1::class.java)
            ContextCompat.startForegroundService(context, serviceIntent)
        }
    }
}