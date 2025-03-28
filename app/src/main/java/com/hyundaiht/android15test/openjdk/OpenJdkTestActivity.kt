package com.hyundaiht.android15test.openjdk

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.hyundaiht.android15test.ui.TextWithButton
import com.hyundaiht.android15test.ui.theme.Android15TestTheme

class OpenJdkTestActivity : ComponentActivity() {
    private val tag = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("mediaProcessing 포그라운드 서비스 유형 추가") {
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("앱이 SYSTEM_ALERT_WINDOW 권한을 보유한 동안 포그라운드 서비스 시작에 적용되는 제한사항") {
                        }
                    }
                }
            }
        }
    }

    fun testRemoveFirstCrash() {
        val list = mutableListOf("a", "b", "c")

        // Kotlin 확장 함수 removeFirst() 사용
        if (Build.VERSION.SDK_INT >= 35) {
            list.removeFirst()
            Log.d(tag, "removeFirst 남은 리스트: $list")
            list.removeLast()
            Log.d(tag, "removeLast 남은 리스트: $list")
        } else {
            Log.d(tag, "SDK_INT < 35")
        }
    }
}