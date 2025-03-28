package com.example.ai_luna_compose

import android.app.Application
import android.content.Intent
import com.example.ai_luna_compose.ui.screen.error.ErrorActivity
import dagger.hilt.android.HiltAndroidApp
import kotlin.system.exitProcess

@HiltAndroidApp
class LunaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            e.printStackTrace()
            val exceptionHandler : GlobalExceptionHandler = ErrorActivityExceptionHandler()
            exceptionHandler.caughtException()
        }
    }

    interface GlobalExceptionHandler {
        fun caughtException()
    }

    inner class ErrorActivityExceptionHandler : GlobalExceptionHandler {
        override fun caughtException() {
            startErrorActivity()
        }

        private fun startErrorActivity() {
            val intent = Intent(applicationContext, ErrorActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            exitProcess(2)
        }
    }
}