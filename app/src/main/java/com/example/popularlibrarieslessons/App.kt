package com.example.popularlibrarieslessons

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.popularlibrarieslessons.data.LoginUseCaseImpl
import com.example.popularlibrarieslessons.data.MockLoginApiImpl
import com.example.popularlibrarieslessons.domain.LoginApi
import com.example.popularlibrarieslessons.domain.LoginUseCase

class App : Application() {
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUseCase: LoginUseCase by lazy {
        LoginUseCaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }