package com.example.popularlibrarieslessons.data

import com.example.popularlibrarieslessons.domain.LoginApi

class MockLoginApiImpl : LoginApi {

    private val usersMutableMap: MutableMap<String, String> =
        mutableMapOf("1" to "1")

    override fun login(login: String, password: String): Boolean {
        Thread.sleep(2_000)
        val savedPass = usersMutableMap[login] ?: return false
        return savedPass == password
    }


    override fun logout(): Boolean {
        Thread.sleep(2_000)
        TODO("Not yet implemented")
    }

    override fun registerNewUser(login: String, password: String): Boolean {
        Thread.sleep(2_000)
        return if (usersMutableMap[login] == null) {
            usersMutableMap[login] = password
            true
        } else false
    }

    override fun forgotPassword(login: String): String? {
        Thread.sleep(2_000)
        return usersMutableMap[login]
    }
}