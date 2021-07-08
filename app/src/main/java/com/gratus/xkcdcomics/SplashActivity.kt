package com.gratus.xkcdcomics

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.gratus.core.BaseApplication
import com.gratus.main.presentation.MainActivity
import com.gratus.ui.base.BaseActivity
import com.gratus.xkcdcomics.databinding.ActivitySplashBinding
import com.gratus.xkcdcomics.di.DaggerSplashComponent

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val splashDisplayLength: Long = 1000

    // move to Main page after 1 second delay
    private fun intentMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onInitDependencyInjection() {
        DaggerSplashComponent
            .builder()
            .coreComponent(BaseApplication.coreComponent)
            .build()
            .inject(this)
    }

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun onInitViewBinding() {
        networkCheck(binding.parentSplash, null)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                intentMainActivity()
            },
            splashDisplayLength
        )
    }
}
