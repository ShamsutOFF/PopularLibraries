package com.example.popularlibrarieslessons.data

import android.os.Handler
import android.util.Log
import androidx.annotation.MainThread
import com.example.popularlibrarieslessons.domain.LoginApi
import com.example.popularlibrarieslessons.domain.LoginUseCase

private const val TAG = "@@@ LoginUseCaseImpl"

class LoginUseCaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUseCase {

    override fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Log.d(
            TAG,
            "login() called with: login = $login, password = $password, callback = $callback"
        )
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                Log.d(TAG, "login() return $result")
                callback(result)
            }
        }.start()
    }

    override fun register(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Log.d(
            TAG,
            "register() called with: login = $login, password = $password, callback = $callback"
        )
        Thread {
            val result = api.registerNewUser(login, password)
            uiHandler.post {
                Log.d(TAG, "register() return $result")
                callback(result)
            }
        }.start()
    }

    override fun forgotPass(
        login: String,
        @MainThread callback: (String?) -> Unit
    ) {
        Log.d(TAG, "forgotPass() called with: login = $login, callback = $callback")
        Thread {
            val result = api.forgotPassword(login)
            uiHandler.post {
                Log.d(TAG, "forgotPass() return $result")
                callback(result)
            }
        }.start()
    }
}