package com.hyundaiht.android15test.openjdk

import android.os.Build
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

class OpenJdkTestActivity : ComponentActivity() {
    private val tag = javaClass.simpleName
    private val openJdkTest = OpenJdkTest()

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
                        TextWithButton("testStringFormatIndexFail 테스트") {
                            runCatching {
                                openJdkTest.testStringFormatIndexFail("text")
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
                        TextWithButton("testStringFormatFlagFail1 테스트") {
                            runCatching {
                                openJdkTest.testStringFormatFlagFail1()
                            }.onSuccess { result ->
                                Log.d(tag, "testStringFormatFlagFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testStringFormatFlagFail error = $error")
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("testStringFormatFlagFail2 테스트") {
                            runCatching {
                                openJdkTest.testStringFormatFlagFail2()
                            }.onSuccess { result ->
                                Log.d(tag, "testStringFormatFlagFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testStringFormatFlagFail error = $error")
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("testStringFormatFlagFail3 테스트") {
                            runCatching {
                                openJdkTest.testStringFormatFlagFail3()
                            }.onSuccess { result ->
                                Log.d(tag, "testStringFormatFlagFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testStringFormatFlagFail error = $error")
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("testStringFormatFlagFail4 테스트") {
                            runCatching {
                                openJdkTest.testStringFormatFlagFail4()
                            }.onSuccess { result ->
                                Log.d(tag, "testStringFormatFlagFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testStringFormatFlagFail error = $error")
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("testArraysAsListToArray 테스트") {
                            runCatching {
                                openJdkTest.testArraysAsListToArrayFail()
                            }.onSuccess { result ->
                                Log.d(tag, "testArraysAsListToArrayFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testArraysAsListToArrayFail error = $error")
                            }

                            runCatching {
                                openJdkTest.testArraysAsListToArraySuccess()
                            }.onSuccess { result ->
                                Log.d(tag, "testArraysAsListToArraySuccess result = ${result.contentToString()}")
                            }.onFailure { error ->
                                Log.d(tag, "testArraysAsListToArraySuccess error = $error")
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                        TextWithButton("testLocaleCodes 테스트") {
                            runCatching {
                                openJdkTest.testLocaleCodesFail()
                            }.onSuccess { result ->
                                Log.d(tag, "testLocaleCodesFail result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testLocaleCodesFail error = $error")
                            }

                            runCatching {
                                openJdkTest.testLocaleCodesSuccess()
                            }.onSuccess { result ->
                                Log.d(tag, "testLocaleCodesSuccess result = $result")
                            }.onFailure { error ->
                                Log.d(tag, "testLocaleCodesSuccess error = $error")
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