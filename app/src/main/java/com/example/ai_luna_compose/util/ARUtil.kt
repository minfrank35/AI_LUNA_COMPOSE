package com.example.ai_luna_compose.util

import android.content.Context
import android.util.Log
import com.google.ar.core.ArCoreApk
import com.google.ar.core.exceptions.UnavailableException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ARUtil {
    companion object {
        /**
         * AR 코어 사용 가능여부 반환
         * true : 사용가능
         * false : 사용불가
         * 
         * suspend function 이므로 코루틴안에서 실행가능함. 
         * ui 변경 필요할시 이용하는 측에서 코루틴 컨텍스트를 Main으로 전환해서 사용하기바람
         */
        suspend fun checkARAvailability(context: Context) : Boolean {
            // 백그라운드 스레드에서 ARCore의 checkAvailability 호출
            val availability = withContext(Dispatchers.IO) {
                ArCoreApk.getInstance().checkAvailability(context)
            }

            return if (availability.isSupported) {
                true
            } else { // 디바이스가 지원되지 않거나 상태를 알 수 없는 경우
                false
            }
        }



        // Verify that ARCore is installed and using the current version.
        //TODO :: 설정
//        fun isARCoreSupportedAndUpToDate(): Boolean {
//            return when (ArCoreApk.getInstance().checkAvailability(this)) {
//                ArCoreApk.Availability.SUPPORTED_INSTALLED -> true
//                ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD, ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED -> {
//                    try {
//                        // Request ARCore installation or update if needed.
//                        when (ArCoreApk.getInstance().requestInstall(this, true)) {
//                            ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
//                                false
//                            }
//                            ArCoreApk.InstallStatus.INSTALLED -> true
//                        }
//                    } catch (e: UnavailableException) {
//                        false
//                    }
//                }
//
//                ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE ->
//                    // This device is not supported for AR.
//                    false
//
//                ArCoreApk.Availability.UNKNOWN_CHECKING -> {
//                    // ARCore is checking the availability with a remote query.
//                    // This function should be called again after waiting 200 ms to determine the query result.
//                }
//                ArCoreApk.Availability.UNKNOWN_ERROR, ArCoreApk.Availability.UNKNOWN_TIMED_OUT -> {
//                    // There was an error checking for AR availability. This may be due to the device being offline.
//                    // Handle the error appropriately.
//                }
//            }
//        }
    }
}