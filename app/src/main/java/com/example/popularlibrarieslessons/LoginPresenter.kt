package com.example.popularlibrarieslessons

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.lang.Thread.sleep

private const val TAG = "@@@ LoginPresenter"


class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess = false
    private var usersDataBase: LoginContract.Model? = Model()

    override fun onAttach(view: LoginContract.View) {

        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
        else {
            view.setNotSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3_000)
            uiHandler.post {
                view?.hideProgress()
                if (usersDataBase?.login(login, password) == true) {
                    view?.setSuccess()
                    isSuccess = true
                } else {
                    view?.showMessage("Неверный пользователь или пароль!")
                    view?.setNotSuccess()
                    isSuccess = false
                }
            }
        }.start()
    }

    override fun onRegister(login: String, password: String) {
        if (usersDataBase?.registerNewUser(login, password) == true){
            view?.showMessage("Пользователь успешно зарегистрирован!")
        } else view?.showMessage("Пользователь уже существует!")
    }

    override fun onForgotLogin(login: String) {
        Log.d(TAG, "onForgotLogin() called with: login = $login")
        val savedPass = usersDataBase?.forgotPassword(login)
        Log.d(TAG, "onForgotLogin(): savedPass = $savedPass")
        if (savedPass != null) {
            view?.showMessage("Ваш пароль: $savedPass")
        } else view?.showMessage("Такого пользователя не существует!")
    }
}