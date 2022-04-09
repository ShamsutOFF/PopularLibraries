package com.example.popularlibrarieslessons.ui.login

import androidx.annotation.MainThread

class LoginContract {
    interface View {
        @MainThread
        fun setSuccess()

        @MainThread
        fun setNotSuccess()

        @MainThread
        fun showMessage(message: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()
    }

    interface Presenter {
        @MainThread
        fun onAttach(view: View)

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