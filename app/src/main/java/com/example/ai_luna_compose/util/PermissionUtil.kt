package com.example.ai_luna_compose.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtil {
    companion object {
        /**
         * 주어진 모든 권한이 허용되었는지 확인합니다.
         * @param context 앱 컨텍스트
         * @param permissions 체크할 권한 리스트
         * @return 모든 권한이 허용되었으면 true, 아니면 false
         */
        fun hasPermissions(context: Context, vararg permissions: String): Boolean {
            return permissions.all { permission ->
                ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
            }
        }

        /**
         * 주어진 권한들을 요청합니다.
         * @param activity 현재 Activity
         * @param permissions 요청할 권한 배열
         * @param requestCode 요청 코드
         */
        fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }

        /**
         * 특정 권한에 대해 사용자에게 권한 설명을 보여줘야 하는지 확인합니다.
         * @param activity 현재 Activity
         * @param permission 확인할 권한
         * @return 권한 설명이 필요한 경우 true, 아니면 false
         */
        fun shouldShowRequestPermissionRationale(activity: Activity, permission: String): Boolean {
            return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
        }
    }
}
