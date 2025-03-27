package com.example.ai_luna_compose.navigation


import android.provider.Settings.Global
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.example.ai_luna_compose.ui.common.GlobalDialogData

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController provided")
}

val LocalAlertDialogState = compositionLocalOf<MutableState<GlobalDialogData?>> {
    error("No AlertDialogState provided")
}
