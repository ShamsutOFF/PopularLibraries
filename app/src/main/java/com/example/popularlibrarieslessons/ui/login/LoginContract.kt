package com.example.popularlibrarieslessons.ui.login

import androidx.annotation.MainThread
import com.example.popularlibrarieslessons.utils.Publisher

class LoginContract {

    interface ViewModel {
        val showProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val messageText: Publisher<String?>

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onRegister(login: String, password: String)

        @MainThread
        fun onForgotLogin(login: String)

        @MainThread
        fun onLogout()
    }
}