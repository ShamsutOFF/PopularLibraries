package com.example.popularlibrarieslessons.ui.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.popularlibrarieslessons.app
import com.example.popularlibrarieslessons.databinding.ActivityMainBinding

private const val TAG = "@@@ MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    private var viewModel: LoginContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        viewModel = initViewModel()
        subscribe()
        bindButtons()
    }

    private fun bindButtons() {
        vb.enterButton.setOnClickListener {
            viewModel?.onLogin(vb.loginTv.text.toString(), vb.passwordTv.text.toString())
        }
        vb.registerButton.setOnClickListener {
            viewModel?.onRegister(vb.loginTv.text.toString(), vb.passwordTv.text.toString())
        }
        vb.forgotPasswordButton.setOnClickListener {
            viewModel?.onForgotLogin(vb.loginTv.text.toString())
        }
    }

    private fun subscribe() {
        Log.d(TAG, "subscribe() called")
        viewModel?.showProgress?.subscribe {
            if (it) showProgress()
            else hideProgress()
        }
        viewModel?.isSuccess?.subscribe {
            if (it) setSuccess()
            else setNotSuccess()
        }
        viewModel?.messageText?.subscribe {
            if (it != null) {
                showMessage(it)
            }
        }
    }

    private fun initViewModel(): LoginViewModel {
        Log.d(TAG, "initViewModel() called")
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel
        return viewModel ?: LoginViewModel(app.loginUseCase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        Log.d(TAG, "onRetainCustomNonConfigurationInstance() called")
        return viewModel
    }

    private fun setSuccess() {
        Log.d(TAG, "setSuccess() called")
        vb.forgotPasswordButton.text = "Выйти из сессии"
        vb.forgotPasswordButton.setOnClickListener {
            setNotSuccess()
        }
        vb.authorizationTv.text = "Поздравляем! Вы аторизовались!"
        Toast.makeText(this, "Поздравляем! Вы аторизовались!", Toast.LENGTH_SHORT).show()
    }

    private fun setNotSuccess() {
        Log.d(TAG, "setNotSuccess() called")
//        viewModel?.onLogout()
        vb.forgotPasswordButton.text = "Забыл пароль"
        vb.forgotPasswordButton.setOnClickListener {
            viewModel?.onForgotLogin(vb.loginTv.text.toString())
        }

        vb.authorizationTv.text = "Введите логин и пароль!"
    }


    private fun showMessage(message: String) {
        Log.d(TAG, "showMessage() called with: message = $message")
        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)
            .setPositiveButton("ОК") { dialog, id ->
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun showProgress() {
        Log.d(TAG, "showProgress() called")
        vb.progressBar.isVisible = true

    }

    private fun hideProgress() {
        Log.d(TAG, "hideProgress() called")
        vb.progressBar.isVisible = false
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() called")
        super.onDestroy()
        viewModel?.isSuccess?.unsubscribeAll()
        viewModel?.showProgress?.unsubscribeAll()
        viewModel?.messageText?.unsubscribeAll()
    }
}
