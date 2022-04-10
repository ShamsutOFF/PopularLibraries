package com.example.popularlibrarieslessons

import android.app.Application
import android.content.Context
import com.example.popularlibrarieslessons.data.MockLoginApiImpl
import com.example.popularlibrarieslessons.domain.LoginApi

class App : Application() {
    val api: LoginApi by lazy { MockLoginApiImpl() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }