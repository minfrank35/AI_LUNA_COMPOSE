package com.example.ai_luna_compose.ui.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ai_luna_compose.ui.common.GlobalDialogData
import com.example.ai_luna_compose.ui.common.GlobalDialogType

open class BaseViewModel : ViewModel() {


    // 전역 AlertDialog 상태를 관리 (null이면 다이얼로그 없음)
    protected val globalDialogData = mutableStateOf<GlobalDialogData?>(null)

//    // 외부에서 읽기 전용으로 접근
//    val globalDialogData: State<GlobalDialogData?> get() = alertDialogData

    fun showGlobalDialog(data: GlobalDialogData) {
        globalDialogData.value = data
    }
    fun dismissGlobalDialog() {
        globalDialogData.value = null
    }

    fun showGlobalErrorDialog(errMsg : String) {
        GlobalDialogData(
            GlobalDialogType.CONFIRM,
            "오류",
            errMsg,
            onConfirm = { dismissGlobalDialog() })
    }
}