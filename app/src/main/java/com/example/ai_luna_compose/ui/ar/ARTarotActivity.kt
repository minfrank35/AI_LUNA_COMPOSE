package com.example.ai_luna_compose.ui.ar

import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.ai_luna_compose.util.PermissionUtil
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException

class ARTarotActivity : ComponentActivity(){
    var mUserRequestedInstall = true
    var mSession : Session? = null

    private val PERMISSION_REQ_CODE = 1000
    override fun onResume() {
        super.onResume()
        if (!PermissionUtil.hasPermissions(this, android.Manifest.permission.CAMERA)) {
            PermissionUtil.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), PERMISSION_REQ_CODE)
        }

        try {
            if (mSession == null) {
                when (ArCoreApk.getInstance().requestInstall(this, mUserRequestedInstall)) {
                    ArCoreApk.InstallStatus.INSTALLED -> {
                        // Success: Safe to create the AR session.
                        createSession()
                    }
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        // When this method returns `INSTALL_REQUESTED`:
                        // 1. ARCore pauses this activity.
                        // 2. ARCore prompts the user to install or update Google Play
                        //    Services for AR (market://details?id=com.google.ar.core).
                        // 3. ARCore downloads the latest device profile data.
                        // 4. ARCore resumes this activity. The next invocation of
                        //    requestInstall() will either return `INSTALLED` or throw an
                        //    exception if the installation or update did not succeed.
                        mUserRequestedInstall = false
                        return
                    }
                }
            }
        } catch (e: UnavailableUserDeclinedInstallationException) {
            // Display an appropriate message to the user and return gracefully.
            Toast.makeText(this, "TODO: handle exception " + e, Toast.LENGTH_LONG)
                .show()
            return
        } catch (e : Exception) {
            return  // mSession remains null, since session creation has failed.
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSession?.close()
        mSession = null
    }
    fun createSession() {
        // Create a new ARCore session.
        mSession = Session(this)

        // Create a session config.
        val config = Config(mSession)

        // Do feature-specific operations here, such as enabling depth or turning on
        // support for Augmented Faces.

        // Configure the session.
        mSession!!.configure(config)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if(requestCode == PERMISSION_REQ_CODE) {
            if (!PermissionUtil.hasPermissions(this, android.Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Camera permission is needed to run AR", Toast.LENGTH_LONG)
                    .show()
                finish()
            }
        }
    }
}