package com.example.popularlibrarieslessons

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.popularlibrarieslessons.databinding.ActivityMainBinding

private const val TAG = "@@@ MainActicity"

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
        vb.forgotPasswordButton.setOnClickListener {
            presenter?.onForgotLogin()
        }
    }

    override fun setSuccess() {
        vb.root.setBackgroundColor(Color.GREEN)
        Toast.makeText(this, "Поздравляем! Вы аторизовались!", Toast.LENGTH_SHORT).show()
    }

    override fun setError(error: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(error)
            .setMessage("Введите правильные логин и пароль или зарегистрируйтесь!")
            .setPositiveButton("ОК") { dialog, id ->
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun initPresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        Log.d(TAG, "initPresenter() called, presenter = $presenter")
        return presenter ?: LoginPresenter()
    }


    override fun onRetainCustomNonConfigurationInstance(): Any? {
        Log.d(TAG, "onRetainCustomNonConfigurationInstance() called, presenter = $presenter")
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