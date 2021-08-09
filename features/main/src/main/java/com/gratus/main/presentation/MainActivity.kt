package com.gratus.main.presentation

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gratus.core.BaseApplication
import com.gratus.main.R
import com.gratus.main.databinding.ActivityMainBinding
import com.gratus.main.di.DaggerMainComponent
import com.gratus.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onInitDependencyInjection() {
        DaggerMainComponent
            .builder()
            .coreComponent(BaseApplication.coreComponent)
            .build()
            .inject(this)
    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onInitViewBinding() {
        networkCheck(binding.parentMain, null)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
    }
}
