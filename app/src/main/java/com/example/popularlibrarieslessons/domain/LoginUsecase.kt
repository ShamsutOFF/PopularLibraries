package com.example.popularlibrarieslessons.domain

import androidx.annotation.MainThread

interface LoginUseCase {
    fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )

    fun register(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )

    fun forgotPass(
        login: String,
        @MainThread callback: (String?) -> Unit
    )
}