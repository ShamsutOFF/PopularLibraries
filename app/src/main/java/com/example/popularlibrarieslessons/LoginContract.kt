package com.example.popularlibrarieslessons

import android.view.View

class LoginContract {
    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: LoginContract.View)
        fun onLogin(login: String, password: String)
        fun onRegister(login: String, password: String)
        fun onForgotLogin()
    }

    interface Model {
        fun registerNewCustomer()
        fun forgotPassword()
        fun login()
    }
}