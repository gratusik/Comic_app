package com.gratus.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.gratus.core.data.cache.AppPreferencesHelper
import com.gratus.core.util.network.AppInterceptor
import com.gratus.core.util.networkManager.NetworkOnlineCheck
import javax.inject.Inject

abstract class BaseFragmentViewModel<B : ViewBinding, VM : BaseViewModel>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> B) :
    Fragment() {

    @Inject
    lateinit var prefs: AppPreferencesHelper

    @Inject
    lateinit var mInterceptor: AppInterceptor

    @Inject
    lateinit var networkOnlineCheck: NetworkOnlineCheck

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private var _binding: B? = null
    val binding get() = _binding!!

    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        onInitViewBinding(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInitDependencyInjection()
        onInitViewModel()
    }

    abstract fun onInitViewModel()
    abstract fun onInitDependencyInjection()
    abstract fun onInitViewBinding(savedInstanceState: Bundle?)

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
