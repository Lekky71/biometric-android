package com.hashcode.biometric

/**
 * Created by HashCode on 1:22 AM 21/09/2018.
 */


import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.support.annotation.RequiresApi


@RequiresApi(api = Build.VERSION_CODES.P)
class BiometricCallbackV28(private val biometricCallback: BiometricCallback) : BiometricPrompt.AuthenticationCallback() {

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        biometricCallback.onAuthenticationSuccessful()
    }


    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence) {
        super.onAuthenticationHelp(helpCode, helpString)
        biometricCallback.onAuthenticationHelp(helpCode, helpString)
    }


    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)
        biometricCallback.onAuthenticationError(errorCode, errString)
    }


    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        biometricCallback.onAuthenticationFailed()
    }
}