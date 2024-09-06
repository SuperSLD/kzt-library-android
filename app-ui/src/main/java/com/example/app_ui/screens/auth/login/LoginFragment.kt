package com.example.app_ui.screens.auth.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.ext.setVisible
import com.example.app_ui.ext.textChanged
import kotlinx.android.synthetic.main.fragment_login.*
import com.example.app_ui.common.core.base.BaseFragment

class LoginFragment : BaseFragment(R.layout.fragment_login), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_edit_text.textChanged {
            login_button.isEnabled = it.isNotEmpty()
        }

        login_button.setOnClickListener {
            presenter.onLogin(login_edit_text.text.toString())
        }
    }

    override fun toggleLoading(isShow: Boolean) {
        login_content.setVisible(!isShow)
        login_pb.setVisible(isShow)
    }

    override fun showErrorToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        presenter.onBack()
    }
}