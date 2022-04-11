package com.example.popularlibrarieslessons.data

import android.util.Log
import com.example.popularlibrarieslessons.domain.LoginApi

private const val TAG ="@@@ MockLoginApiImpl"

class MockLoginApiImpl : LoginApi {

    private val usersMutableMap: MutableMap<String, String> =
        mutableMapOf("1" to "1")

    override fun login(login: String, password: String): Boolean {
        Log.d(TAG, "login() called with: login = $login, password = $password")
        Thread.sleep(2_000)
        val savedPass = usersMutableMap[login] ?: return false
        return savedPass == password
    }


    override fun logout(): Boolean {
        Log.d(TAG, "logout() called")
        Thread.sleep(2_000)
        return true
    }

    override fun registerNewUser(login: String, password: String): Boolean {
        Log.d(TAG, "registerNewUser() called with: login = $login, password = $password")
        Thread.sleep(2_000)
        return if (usersMutableMap[login] == null) {
            usersMutableMap[login] = password
            true
        } else false
    }

    override fun forgotPassword(login: String): String? {
        Log.d(TAG, "forgotPassword() called with: login = $login")
        Thread.sleep(2_000)
        return usersMutableMap[login]
    }
}