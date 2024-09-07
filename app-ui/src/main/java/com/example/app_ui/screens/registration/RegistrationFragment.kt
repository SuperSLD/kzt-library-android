package com.example.app_ui.screens.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
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

        setOnTextListener()

        registration_button.setOnClickListener {
            presenter.onRegistration(
                name_edit_text.text.toString(),
                lastname_edit_text.text.toString(),
                mid_name_edit_text.text.toString(),
                avatar_edit_text.text.toString()
            )
        }
    }

    private fun setOnTextListener() {
        name_edit_text.textChanged {
            presenter.onNameChange(it)
        }

        lastname_edit_text.textChanged {
            presenter.onLastnameChange(it)
        }

        mid_name_edit_text.textChanged {
            presenter.onMidNameChange(it)
        }
    }

    @ProvidePresenter
    fun providePresenter() = RegistrationPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as RegistrationScreen,
    )

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

    override fun toggleButtonEnabled(isEnabled: Boolean) {
        registration_button.isEnabled = isEnabled
    }

    companion object {
        fun newInstance(screen: RegistrationScreen) = RegistrationFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }
}