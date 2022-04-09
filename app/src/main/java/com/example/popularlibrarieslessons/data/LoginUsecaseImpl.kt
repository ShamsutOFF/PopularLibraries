package com.example.popularlibrarieslessons.data

import android.os.Handler
import androidx.annotation.MainThread
import com.example.popularlibrarieslessons.domain.LoginApi
import com.example.popularlibrarieslessons.domain.LoginUseCase

class LoginUseCaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUseCase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun register(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = api.registerNewUser(login, password)
            uiHandler.post { callback(result) }
        }.start()
    }

    override fun forgotPass(
        login: String,
        callback: (String?) -> Unit
    ) {
        Thread {
            val result = api.forgotPassword(login)
            uiHandler.post { callback(result) }
        }
    }
}