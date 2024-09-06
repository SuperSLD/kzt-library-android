package com.example.app_ui.screens.main.profile.achivments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.app_domain.models.user.Achivment
import com.example.app_ui.R
import com.example.app_ui.common.ARG_KEY_SCREEN_PARAMS
import com.example.app_ui.common.view.simplerecycler.SimpleRecyclerAdapter
import com.example.app_ui.screens.main.profile.achivments.achivmentdetail.AchivmentDetailBSFragment
import com.example.app_ui.screens.main.profile.holders.AchivmentViewHolder
import kotlinx.android.synthetic.main.fragment_achivments.*
import com.example.app_ui.common.core.base.BaseFragment

class AchivmentsFragment : BaseFragment(R.layout.fragment_achivments), AchivmentsView {

    companion object {
        fun newInstance(screen: AchivmentsScreen) = AchivmentsFragment().apply {
            arguments = bundleOf(ARG_KEY_SCREEN_PARAMS to screen)
        }
    }

    @InjectPresenter
    lateinit var presenter: AchivmentsPresenter

    @ProvidePresenter
    fun providePresenter() = AchivmentsPresenter(
        requireArguments().getSerializable(ARG_KEY_SCREEN_PARAMS) as AchivmentsScreen,
    )

    private val achivmentsAdapter by lazy {
        SimpleRecyclerAdapter(
            R.layout.viewholder_achivment_second,
            { AchivmentViewHolder(it) },
            { item, _ -> openAchivmentDetail(item) },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        achivments_toolbar.init(
            title = getString(R.string.achivments_title),
            back = this::onBackPressed
        )

        with(achivments_recycler) {
            adapter = achivmentsAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun openAchivmentDetail(achivment: Achivment) {
        val bottomSheet = AchivmentDetailBSFragment.newInstance(achivment)
        bottomSheet.show(childFragmentManager, "ach")
    }

    override fun onBackPressed() {
        presenter.onBack()
    }

    override fun showAchivments(items: List<Achivment>) {
        achivmentsAdapter.swapItems(items)
    }

}