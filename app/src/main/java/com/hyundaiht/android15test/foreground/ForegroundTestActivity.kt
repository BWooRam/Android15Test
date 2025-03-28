package com.hyundaiht.android15test.foreground

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.os.postDelayed
import com.hyundaiht.android15test.R
import com.hyundaiht.android15test.ui.TextWithButton
import com.hyundaiht.android15test.ui.theme.Android15TestTheme

class ForegroundTestActivity : ComponentActivity() {
    private var windowManager: WindowManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            Android15TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        TextWithButton("dataSync 포그라운드 서비스 유형 동작 변경") {
                            executeDataSyncForegroundService(this@ForegroundTestActivity, "STICKY")
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("mediaProcessing 포그라운드 서비스 유형 추가") {
                            executeMediaProcessingForegroundService(
                                this@ForegroundTestActivity,
                                "STICKY"
                            )
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("앱이 SYSTEM_ALERT_WINDOW 권한을 보유한 동안 포그라운드 서비스 시작에 적용되는 제한사항") {
                            executeMediaProcessingForegroundService(context)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun executeMediaProcessingForegroundService(context: Context) {
        //권한 체크 및 설정 요청 코드
        if (!Settings.canDrawOverlays(context)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + context.packageName)
            )
            startActivity(intent)
        } else {
            val overlayView = OverlayView(context)

            val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
            ).apply {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                x = 0
                y = 200 // 위치 조절
            }

            windowManager?.addView(overlayView, params)
        }
    }
}

fun executeDataSyncForegroundService(context: Context, mode: String) {
    val intent = Intent(context, ForegroundTestService1::class.java)
    intent.putExtra("MODE", mode)
    context.startService(intent)
}

fun executeMediaProcessingForegroundService(context: Context, mode: String) {
    val intent = Intent(context, ForegroundTestService2::class.java)
    intent.putExtra("MODE", mode)
    context.startService(intent)
}

fun executeForegroundStartReceiver(context: Context) {
    val intent = Intent(context, ForegroundStartReceiver::class.java).apply {
        action = ForegroundStartReceiver.ACTION_START_FOREGROUND
    }
    context.sendBroadcast(intent)
}