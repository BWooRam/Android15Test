package com.hyundaiht.android15test.dnd

import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
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
import com.hyundaiht.android15test.ui.TextWithButton
import com.hyundaiht.android15test.ui.theme.Android15TestTheme


class DndTestActivity : ComponentActivity() {
    private val tag = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            Android15TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        TextWithButton("notificationPolicy 테스트") {
                            runCatching {
                                val policy = NotificationManager.Policy(
                                    NotificationManager.Policy.PRIORITY_CATEGORY_CALLS,  // 전화 허용
                                    NotificationManager.Policy.PRIORITY_CATEGORY_MESSAGES,  // 메시지 허용
                                    NotificationManager.Policy.PRIORITY_CATEGORY_EVENTS // 이벤트 허용
                                )
                                //DND 모드 중에서도 특정 알림(전화, 메시지 등)을 허용
                                notificationManager.notificationPolicy = policy
                            }.onSuccess { result ->
                                Log.d(tag, "testStringFormatFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testStringFormatFail error = $error")
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("setInterruptionFilter 테스트") {
                            runCatching {
                                //	DND 모드의 전체 알림 허용 범위를 설정 (소리, 진동 등)
                                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY);
                            }.onSuccess { result ->
                                Log.d(tag, "testStringFormatFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testStringFormatFail error = $error")
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                    }
                }
            }
        }
    }
}