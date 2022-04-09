package com.example.popularlibrarieslessons.ui.login

import android.os.Handler
import android.os.Looper
import com.example.popularlibrarieslessons.domain.LoginApi

class LoginPresenter(private val api: LoginApi) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess = false

    override fun onAttach(view: LoginContract.View) {

        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setNotSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            uiHandler.post {
                if (api.login(login, password) == true) {
                    view?.setSuccess()
                    isSuccess = true
                    view?.hideProgress()
                } else {
                    view?.showMessage("Неверный пользователь или пароль!")
                    view?.setNotSuccess()
                    isSuccess = false
                    view?.hideProgress()
                }
            }
        }.start()
    }

    override fun onRegister(login: String, password: String) {
        view?.showProgress()
        Thread {
            uiHandler.post {
                if (api.registerNewUser(login, password) == true) {
                    view?.showMessage("Пользователь успешно зарегистрирован!")
                    view?.hideProgress()
                } else {
                    view?.showMessage("Пользователь уже существует!")
                    view?.hideProgress()
                }
            }

        }.start()

    }

    override fun onForgotLogin(login: String) {
        val savedPass = api.forgotPassword(login)
        if (savedPass != null) {
            view?.showMessage("Ваш пароль: $savedPass")
        } else view?.showMessage("Такого пользователя не существует!")
    }

    override fun onLogout() {
        isSuccess = false
    }
}