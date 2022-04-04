package com.example.popularlibrarieslessons

class Model : LoginContract.Model {

    private val usersMutableMap: MutableMap<String, String> =
        mutableMapOf("1" to "1")

    override fun registerNewUser(login: String, password: String): Boolean {
        if (usersMutableMap[login] == null) {
            usersMutableMap[login] = password
            return true
        } else return false
    }

    override fun forgotPassword(login: String): String? {
        return usersMutableMap[login]
    }

    override fun login(login: String, password: String): Boolean {
        val savedPass = usersMutableMap[login] ?: return false
        return savedPass == password
    }
}