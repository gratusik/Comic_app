package com.gratus.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.gratus.core.data.cache.AppPreferencesHelper
import com.gratus.core.util.network.AppInterceptor
import com.gratus.core.util.networkManager.NetworkOnlineCheck
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> B) :
    Fragment() {

    @Inject
    lateinit var prefs: AppPreferencesHelper

    @Inject
    lateinit var mInterceptor: AppInterceptor

    @Inject
    lateinit var networkOnlineCheck: NetworkOnlineCheck

    private var _binding: B? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViewBinding()
    }

    abstract fun onInitViewBinding()

    fun isNetworkConnected(): Boolean {
        return networkOnlineCheck.isNetworkOnline
    }

    fun getPreferences(): AppPreferencesHelper {
        return prefs
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
