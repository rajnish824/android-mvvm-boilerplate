package com.devrajnish.baseapplication.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devrajnish.baseapplication.ui.dashboard.view.DashboardActivity

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DashboardActivity.launch(this@OnBoardingActivity)
        finish()
    }
}