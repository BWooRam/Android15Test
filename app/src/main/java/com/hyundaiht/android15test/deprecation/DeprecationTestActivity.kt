package com.hyundaiht.android15test.deprecation

import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.Spatializer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hyundaiht.android15test.ui.TextWithButton
import com.hyundaiht.android15test.ui.theme.Android15TestTheme


class DeprecationTestActivity : ComponentActivity() {
    private val tag = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var rememberWebViewType by remember { mutableIntStateOf(0) }
            val context = LocalContext.current
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            Android15TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(scrollState)
                    ) {
                        TextWithButton("Spatializer 테스트") {
                            runCatching {
                                // AudioManager 인스턴스 가져오기
                                val audioManager =
                                    getSystemService(Context.AUDIO_SERVICE) as AudioManager

                                // Spatializer 인스턴스 가져오기
                                val spatializer =
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) {
                                        audioManager.spatializer
                                    } else {
                                        null
                                    }

                                if (spatializer == null) {
                                    Log.e(tag, "Spatializer not supported on this device")
                                    return@runCatching
                                }

                                val isAvailable =
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) {
                                        spatializer.isAvailable
                                    } else {
                                        false
                                    }

                                // 공간화 가능 여부 확인
                                if (isAvailable) {
                                    Log.i(tag, "Spatializer is available.")
                                } else {
                                    Log.i(tag, "Spatializer is not available.")
                                    return@runCatching
                                }

                                // 공간화 활성화 여부 확인
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) {
                                    if (spatializer.isEnabled) {
                                        Log.i(tag, "Spatializer is already enabled.")
                                    } else {
                                        Log.i(tag, "Enabling spatial audio.")
                                    }
                                }

                                // 공간 오디오가 헤드폰에서 활성화 가능한지 확인
                                val canBeSpatialized =
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) {
                                        // 오디오 특성 설정 (예: 미디어 사용)
                                        val audioAttributes = AudioAttributes.Builder()
                                            .setUsage(AudioAttributes.USAGE_MEDIA)
                                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                            .build()

                                        // 오디오 포맷 설정 (예: 스테레오, 16비트 PCM)
                                        val audioFormat = AudioFormat.Builder()
                                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                                            .setSampleRate(44100)
                                            .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO).build()

                                        // 공간화 가능 여부 확인
                                        val canSpatialize = spatializer.canBeSpatialized(
                                            audioAttributes, audioFormat
                                        )
                                        if (canSpatialize) {
                                            Log.i(
                                                tag,
                                                "Spatial audio can be applied to the given format and attributes."
                                            )
                                            true
                                        } else {
                                            Log.i(
                                                tag,
                                                "Spatial audio cannot be applied to this format or attributes."
                                            )
                                            false
                                        }
                                    } else {
                                        Log.i(tag, "Can't Spatial audio ")
                                        false
                                    }

                                if (canBeSpatialized) {
                                    Log.i(
                                        tag, "Spatial audio can be applied to media on headphones."
                                    )
                                } else {
                                    Log.i(tag, "Spatial audio cannot be applied to this output.")
                                }

                                // 공간화 상태 변화 감지 리스너
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) {
                                    spatializer.addOnSpatializerStateChangedListener(mainExecutor,
                                        object : Spatializer.OnSpatializerStateChangedListener {
                                            override fun onSpatializerEnabledChanged(
                                                spat: Spatializer, enabled: Boolean
                                            ) {
                                                Log.i(
                                                    tag,
                                                    "Spatializer onSpatializerEnabledChanged enabled = $enabled, spat = $spat"
                                                )
                                            }

                                            override fun onSpatializerAvailableChanged(
                                                spat: Spatializer, available: Boolean
                                            ) {
                                                Log.i(
                                                    tag,
                                                    "Spatializer onSpatializerAvailableChanged available = $available, spat = $spat"
                                                )
                                            }
                                        })
                                }
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
                        TextWithButton("WebView  테스트") {
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
                        WebViewSwapButton(
                            onIndexedDb = { rememberWebViewType = 0 },
                            onWebStorage = { rememberWebViewType = 1 }
                        )
                        when(rememberWebViewType){
                            0 -> WebViewScreen("file:///android_asset/indexeddb.html")
                            1 -> WebViewScreen("file:///android_asset/webstorage.html")
                            else -> throw NullPointerException()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun WebViewSwapButton(onIndexedDb: () -> Unit, onWebStorage: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Button(
                modifier = Modifier.wrapContentSize(), onClick = onIndexedDb
            ) {
                Text("indexedDB")
            }
            Button(
                modifier = Modifier.wrapContentSize(), onClick = onWebStorage
            ) {
                Text("WebStorage")
            }
        }

    }

    @Composable
    fun WebViewScreen(url: String) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.databaseEnabled = true
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                loadUrl(url)
            }
        }, update = { webView ->
            webView.loadUrl(url)
        })
    }
}