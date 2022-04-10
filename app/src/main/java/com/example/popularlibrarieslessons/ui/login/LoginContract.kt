package com.example.popularlibrarieslessons.ui.login

class LoginContract {
    interface View {
        fun setSuccess()
        fun setNotSuccess()
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onRegister(login: String, password: String)
        fun onForgotLogin(login: String)
        fun onLogout()
    }
}