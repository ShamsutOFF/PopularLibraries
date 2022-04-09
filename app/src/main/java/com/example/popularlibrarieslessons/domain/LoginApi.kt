package com.example.popularlibrarieslessons.domain

import androidx.annotation.WorkerThread

interface LoginApi {
    @WorkerThread
    fun login(login: String, password: String): Boolean

    @WorkerThread
    fun logout(): Boolean

    @WorkerThread
    fun registerNewUser(login: String, password: String): Boolean

    @WorkerThread
    fun forgotPassword(login: String): String?
}