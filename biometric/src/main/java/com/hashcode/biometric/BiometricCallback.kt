package com.hashcode.biometric

/**
 * Created by HashCode on 1:21 AM 21/09/2018.
 */
interface BiometricCallback {

    fun onSdkVersionNotSupported()

    fun onBiometricAuthenticationNotSupported()

    fun onBiometricAuthenticationNotAvailable()

    fun onBiometricAuthenticationPermissionNotGranted()

    fun onBiometricAuthenticationInternalError(error: String)

    fun onAuthenticationFailed()

    fun onAuthenticationCancelled()

    fun onAuthenticationSuccessful()

    fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence)

    fun onAuthenticationError(errorCode: Int, errString: CharSequence)
}