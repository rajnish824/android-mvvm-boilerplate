package com.devrajnish.baseapplication.ui.dashboard.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.devrajnish.baseapplication.base.BaseActivity
import com.devrajnish.baseapplication.databinding.ActivityDashboardBinding
import com.devrajnish.baseapplication.ui.dashboard.viewmodel.DashboardViewModel
import com.devrajnish.baseapplication.utils.AppUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : BaseActivity<DashboardViewModel, ActivityDashboardBinding>() {
    companion object {
        fun launch(context: Context, isCleanRequired: Boolean = false) {
            val intent = Intent(context, DashboardActivity::class.java)
            if (isCleanRequired)
                intent.flags = AppUtils.addCleanIntent()

            context.startActivity(intent)
        }
    }

    override val viewModel: DashboardViewModel by viewModel()

    override fun setBinding(layoutInflater: LayoutInflater) =
        ActivityDashboardBinding.inflate(layoutInflater)
}