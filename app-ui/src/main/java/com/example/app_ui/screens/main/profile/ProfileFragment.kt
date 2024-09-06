package com.example.app_ui.screens.main.profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.example.app_domain.models.Idea
import com.example.app_domain.models.user.User
import com.example.app_ui.R
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.ext.setVisible
import com.example.app_ui.screens.main.profile.holders.AchivmentViewHolder
import com.example.app_ui.screens.main.profile.holders.IdeaViewHolder
import kotlinx.android.synthetic.main.fragment_profile.*
import com.example.app_ui.common.core.base.BaseFragment
import com.example.app_ui.common.core.base.addSystemTopPadding

class ProfileFragment : BaseFragment(R.layout.fragment_profile), ProfileView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private val achivmentsAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_achivment,
            { AchivmentViewHolder(it) },
            { _, _ -> presenter.onOpenAchivments() },
        )
    }
    private val ideasAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_idea,
            { IdeaViewHolder(it) },
            { _, _ ->  },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile_container.addSystemTopPadding()

        with(profile_ideas_adapter) {
            adapter = ideasAdapter
            layoutManager = LinearLayoutManager(context)
        }

        with(profile_ach_recycler) {
            adapter = achivmentsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showIdeas(items: List<Idea>) {
        ideasAdapter.swapItems(items)
    }

    override fun toggleLoading(show: Boolean) {
        profile_nested.setVisible(!show)
        profile_loading.setVisible(show)
    }

    override fun showProfile(user: User) {
        profile_name.text = user.nameString()
        profile_company_role.text = user.companyRole
        profile_coins.text = getString(R.string.central_coins, user.coins)
        Glide.with(requireContext()).load(user.avatar).into(profile_avatar)
        achivmentsAdapter.swapItems(user.achivements.sortedBy { !it.done })
    }
}