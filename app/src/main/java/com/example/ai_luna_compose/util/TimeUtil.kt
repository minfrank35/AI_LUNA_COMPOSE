package com.example.ai_luna_compose.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object TimeUtil {
    /**
     * 현재 시간을 "HH:mm" 형식(예: "09:24")으로 반환합니다.
     */
    fun getCurrentTime(): String {
        val now = Date()
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(now)
    }
}