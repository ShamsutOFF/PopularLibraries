package com.example.popularlibrarieslessons

import android.view.View

class LoginContract {
    interface View {
        fun setSuccess()
        fun setNotSuccess()
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: LoginContract.View)
        fun onLogin(login: String, password: String)
        fun onRegister(login: String, password: String)
        fun onForgotLogin(login: String)
    }

    interface Model {
        fun registerNewUser(login: String, password: String): Boolean
        fun forgotPassword(login: String): String?
        fun login(login: String, password: String): Boolean
    }
}