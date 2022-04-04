package com.example.popularlibrarieslessons

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep


class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess = false

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setError("Неверный пароль!")
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3_000)
            uiHandler.post {
                view?.hideProgress()
                if (login == password) {
                    view?.setSuccess()
                    isSuccess = true
                } else {
                    view?.setError("Неверный пароль!")
                    isSuccess = false
                }
            }
        }.start()
    }

    override fun onRegister(login: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun onForgotLogin() {
        TODO("Not yet implemented")
    }
}