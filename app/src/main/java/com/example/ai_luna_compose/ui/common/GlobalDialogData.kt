package com.example.ai_luna_compose.ui.common


enum class GlobalDialogType {
    CONFIRM,
    DISMISS_CONFIRM
}
data class GlobalDialogData(
    val type: GlobalDialogType,
    val title: String,
    val message: String,
    val onConfirm: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null
)
