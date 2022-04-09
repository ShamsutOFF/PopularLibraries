package com.example.popularlibrarieslessons.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.popularlibrarieslessons.app
import com.example.popularlibrarieslessons.data.LoginUseCaseImpl
import com.example.popularlibrarieslessons.databinding.ActivityMainBinding
import com.example.popularlibrarieslessons.domain.LoginUseCase

private const val TAG = "@@@ MainActivity"

class MainActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var vb: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        presenter = initPresenter()
        presenter?.onAttach(this)

        vb.enterButton.setOnClickListener {
            presenter?.onLogin(vb.loginTv.text.toString(), vb.passwordTv.text.toString())
        }
        vb.registerButton.setOnClickListener {
            presenter?.onRegister(vb.loginTv.text.toString(), vb.passwordTv.text.toString())
        }
    }

    override fun setSuccess() {
        vb.forgotPasswordButton.text = "Выйти из сессии"
        vb.forgotPasswordButton.setOnClickListener {
            setNotSuccess()
        }

        vb.authorizationTv.text = "Поздравляем! Вы аторизовались!"
        Toast.makeText(this, "Поздравляем! Вы аторизовались!", Toast.LENGTH_SHORT).show()
    }

    override fun setNotSuccess() {
        presenter?.onLogout()
        vb.forgotPasswordButton.text = "Забыл пароль"
        vb.forgotPasswordButton.setOnClickListener {
            presenter?.onForgotLogin(vb.loginTv.text.toString())
        }

        vb.authorizationTv.text = "Введите логин и пароль!"
    }

    override fun showMessage(message: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)
            .setPositiveButton("ОК") { dialog, id ->
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun initPresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter(app.loginUseCase)
    }


    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun showProgress() {
        Log.d(TAG, "showProgress() called")
        vb.progressBar.isVisible = true

    }

    override fun hideProgress() {
        Log.d(TAG, "hideProgress() called")
        vb.progressBar.isVisible = false
    }
}