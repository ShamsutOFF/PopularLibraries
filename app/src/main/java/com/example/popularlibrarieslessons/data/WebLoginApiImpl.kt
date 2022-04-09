package com.example.popularlibrarieslessons.data

import com.example.popularlibrarieslessons.domain.LoginApi

class WebLoginApiImpl: LoginApi {
    override fun login(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun registerNewUser(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(login: String): String? {
        TODO("Not yet implemented")
    }
}