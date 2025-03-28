package com.hyundaiht.android15test.foreground

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class OverlayView(context: Context) : FrameLayout(context) {
    private val textView: TextView

    init {
        // 배경 및 텍스트 설정
        setBackgroundColor(Color.parseColor("#AA000000")) // 반투명 배경

        textView = TextView(context).apply {
            text = "Overlay 표시 중"
            setTextColor(Color.WHITE)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            setPadding(24, 24, 24, 24)
        }

        addView(textView)
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)

        when (visibility) {
            View.VISIBLE -> {
                Log.d("OverlayView", "✅ Overlay is VISIBLE")
                textView.text = "Overlay 표시 중 (VISIBLE)"
                executeForegroundStartReceiver(context)
            }
            View.GONE -> {
                Log.d("OverlayView", "❌ Overlay is GONE")
                textView.text = "Overlay 사라짐 (GONE)"
            }
            View.INVISIBLE -> {
                Log.d("OverlayView", "⚠️ Overlay is INVISIBLE")
                textView.text = "Overlay 비가시 (INVISIBLE)"
            }
        }
    }
} 
