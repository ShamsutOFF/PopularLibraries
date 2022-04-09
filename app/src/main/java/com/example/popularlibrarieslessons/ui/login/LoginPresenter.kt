package com.example.popularlibrarieslessons.ui.login

import com.example.popularlibrarieslessons.domain.LoginUseCase

class LoginPresenter(private val loginUseCase: LoginUseCase) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
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
        loginUseCase.login(login, password) { result ->
            view?.hideProgress()
            if (result) {
                view?.setSuccess()
                isSuccess = true
            } else {
                view?.showMessage("Неверный пользователь или пароль!")
                view?.setNotSuccess()
                isSuccess = false
            }
        }
    }

    override fun onRegister(login: String, password: String) {
        view?.showProgress()
        loginUseCase.register(login, password) { result ->
            if (result) {
                view?.showMessage("Пользователь успешно зарегистрирован!")
                view?.hideProgress()
            } else {
                view?.showMessage("Пользователь уже существует!")
                view?.hideProgress()
            }
        }
    }

    override fun onForgotLogin(login: String) {
        loginUseCase.forgotPass(login) { result ->
            if (result != null) {
                view?.showMessage("Ваш пароль: $result")
            } else view?.showMessage("Такого пользователя не существует!")
        }
    }

    override fun onLogout() {
        isSuccess = false
    }
}