package com.sec

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.concurrent.Executor

class BiometricAuth(context: Context) {

    private var biometricManager : BiometricManager = BiometricManager.from(context)
    private var executor : Executor = ContextCompat.getMainExecutor(context)

    fun biometricAuth(fragment: Fragment,callback : (BiometricResponse) -> Unit){
        when(biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)){
            BiometricManager.BIOMETRIC_SUCCESS -> {
                validBiometric(fragment,callback)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                callback.invoke(BiometricResponse.SUCCESS)
            }
            else -> {
                callback.invoke(BiometricResponse.ERROR)
            }
        }
    }

    private fun validBiometric(fragment: Fragment,callback : (BiometricResponse) -> Unit){
        val biometricPrompt = BiometricPrompt(
            fragment,
            executor,
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    callback.invoke(BiometricResponse.ERROR)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    callback.invoke(BiometricResponse.SUCCESS)

                }
            })

        biometricPrompt.authenticate(
            PromptInfo.Builder()
                .setTitle("Put your fingerprint")
                .setNegativeButtonText("Cancel")
                .build()
        )
    }


}