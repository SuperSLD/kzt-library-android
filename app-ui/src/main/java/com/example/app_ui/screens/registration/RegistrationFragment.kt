package com.example.app_ui.screens.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.ext.setVisible
import com.example.app_ui.ext.textChanged
import kotlinx.android.synthetic.main.fragment_registration.avatar_edit_text
import kotlinx.android.synthetic.main.fragment_registration.lastname_edit_text
import kotlinx.android.synthetic.main.fragment_registration.mid_name_edit_text
import kotlinx.android.synthetic.main.fragment_registration.name_edit_text
import kotlinx.android.synthetic.main.fragment_registration.registration_button
import kotlinx.android.synthetic.main.fragment_registration.registration_content
import kotlinx.android.synthetic.main.fragment_registration.registration_pb

class RegistrationFragment: BaseFragment(R.layout.fragment_registration), RegistrationView {

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var name = ""
        var lastName = ""
        var midName = ""
        var avatar: String? = null

        name_edit_text.textChanged {
            name = it
        }

        lastname_edit_text.textChanged {
            lastName = it
        }

        mid_name_edit_text.textChanged {
            midName = it
        }

        avatar_edit_text.textChanged {
            if (it.isNotEmpty()) avatar = it
        }

        registration_button.isEnabled = name.isNotEmpty() && lastName.isNotEmpty() && midName.isNotEmpty()

        registration_button.setOnClickListener {
            presenter.onRegistration(name, lastName, midName, avatar)
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun toggleLoading(isShow: Boolean) {
        registration_content.setVisible(!isShow)
        registration_pb.setVisible(isShow)
    }

    override fun showErrorToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(screen: RegistrationScreen) = RegistrationFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }
}