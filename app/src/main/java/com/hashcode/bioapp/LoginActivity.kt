package com.hashcode.bioapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.hashcode.biometric.BiometricCallback
import com.hashcode.biometric.BiometricManager


class LoginActivity : AppCompatActivity(), BiometricCallback {

    lateinit var scanButton:Button
    lateinit var usernameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        scanButton = findViewById(R.id.scanButton)
        usernameEditText = findViewById(R.id.usernameEditText)
        scanButton.setOnClickListener(scanButtonClickListener)
        usernameEditText.addTextChangedListener(textWatcher)
    }

    private val scanButtonClickListener = View.OnClickListener { _ ->
        val biometricManager = BiometricManager.BiometricBuilder(this@LoginActivity)
                .setTitle("App authentication")
                .setSubtitle("sign in with your fingerprint")
                .setDescription("Place your finger on your fingerprint scanner")
                .setNegativeButtonText("Cancel")
                .build()
                .authenticate(this@LoginActivity)
    }

    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            scanButton.isEnabled = !s.isEmpty()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }


    override fun onSdkVersionNotSupported() {
        Toast.makeText(applicationContext, getString(R.string.biometric_error_sdk_not_supported), Toast.LENGTH_LONG).show()
    }

    override fun onBiometricAuthenticationNotSupported() {
        Toast.makeText(applicationContext, getString(R.string.biometric_error_hardware_not_supported), Toast.LENGTH_LONG).show()
    }

    override fun onBiometricAuthenticationNotAvailable() {
        Toast.makeText(applicationContext, getString(R.string.biometric_error_fingerprint_not_available), Toast.LENGTH_LONG).show()
    }

    override fun onBiometricAuthenticationPermissionNotGranted() {
        Toast.makeText(applicationContext, getString(R.string.biometric_error_permission_not_granted), Toast.LENGTH_LONG).show()
    }

    override fun onBiometricAuthenticationInternalError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationFailed() {
                Toast.makeText(applicationContext, getString(R.string.biometric_failure), Toast.LENGTH_LONG).show();
    }

    override fun onAuthenticationCancelled() {
        Toast.makeText(applicationContext, getString(R.string.biometric_cancelled), Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationSuccessful() {
        Toast.makeText(applicationContext, getString(R.string.biometric_success), Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence) {
                Toast.makeText(applicationContext, helpString, Toast.LENGTH_LONG).show();
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                Toast.makeText(applicationContext, errString, Toast.LENGTH_LONG).show();
    }
}
