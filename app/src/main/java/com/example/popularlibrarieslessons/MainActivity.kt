package com.example.popularlibrarieslessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.popularlibrarieslessons.databinding.ActivityMainBinding

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
//            .setMessage("Введите правильные логин и пароль или зарегистрируйтесь!")
            .setPositiveButton("ОК") { dialog, id ->
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun initPresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }


    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun showProgress() {
        vb.shimmerViewContainer.alpha = 0.7F
        vb.shimmerViewContainer.startShimmer()
    }

    override fun hideProgress() {
        vb.shimmerViewContainer.alpha = 1F
        vb.shimmerViewContainer.stopShimmer()
    }
}