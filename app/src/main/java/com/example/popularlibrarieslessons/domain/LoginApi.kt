package com.example.popularlibrarieslessons.domain

interface LoginApi {
    fun login(login: String, password: String): Boolean
    fun logout(): Boolean
    fun registerNewUser(login: String, password: String): Boolean
    fun forgotPassword(login: String): String?
}