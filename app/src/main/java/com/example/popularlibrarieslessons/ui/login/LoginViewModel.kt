package com.example.popularlibrarieslessons.ui.login

import android.util.Log
import com.example.popularlibrarieslessons.domain.LoginUseCase
import com.example.popularlibrarieslessons.utils.Publisher

private const val TAG = "@@@ LoginViewModel"

class LoginViewModel(private val loginUseCase: LoginUseCase) : LoginContract.ViewModel {
    override val showProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val messageText: Publisher<String?> = Publisher()

    override fun onLogin(login: String, password: String) {
        Log.d(TAG, "onLogin() called with: login = $login, password = $password")
        showProgress.post(true)
        loginUseCase.login(login, password) { result ->
            showProgress.post(false)
            if (result) {
                isSuccess.post(true)
            } else {
                messageText.post("Неверный пользователь или пароль!")
                isSuccess.post(false)
            }
        }
    }

    override fun onRegister(login: String, password: String) {
        Log.d(TAG, "onRegister() called with: login = $login, password = $password")
        showProgress.post(true)
        loginUseCase.register(login, password) { result ->
            if (result) {
                messageText.post("Пользователь успешно зарегистрирован!")
                showProgress.post(false)
            } else {
                messageText.post("Пользователь уже существует!")
                showProgress.post(false)
            }
        }
    }

    override fun onForgotLogin(login: String) {
        Log.d(TAG, "onForgotLogin() called with: login = $login")
        showProgress.post(true)
        loginUseCase.forgotPass(login) { result ->
            if (result != null) {
                showProgress.post(false)
                messageText.post("Ваш пароль: $result")
            } else {
                showProgress.post(false)
                messageText.post("Такого пользователя не существует!")
            }
        }
    }

    override fun onLogout() {
        Log.d(TAG, "onLogout() called")
        isSuccess.post(false)
    }
}