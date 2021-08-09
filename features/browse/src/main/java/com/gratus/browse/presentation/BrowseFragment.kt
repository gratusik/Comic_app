package com.gratus.browse.presentation

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.viewpager2.widget.ViewPager2
import com.gratus.browse.R
import com.gratus.browse.databinding.FragmentBrowseBinding
import com.gratus.browse.di.DaggerBrowseComponent
import com.gratus.browse.util.PageSelectUtil
import com.gratus.core.BaseApplication
import com.gratus.core.util.CoreConstants.BrowseConstants.FIRST_PAGE
import com.gratus.core.util.CoreConstants.BrowseConstants.LAST_PAGE
import com.gratus.core.util.CoreConstants.BrowseConstants.RANDOM_PAGE
import com.gratus.core.util.CoreConstants.CommonConstant.COMIC_ID
import com.gratus.core.util.CoreConstants.IntentConstants.NUM
import com.gratus.core.util.CoreConstants.IntentConstants.PAGE_ID
import com.gratus.core.util.CoreConstants.RemoteConstant.BASE_URL
import com.gratus.core.util.network.ResourceState
import com.gratus.ui.base.BaseFragmentViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast

class BrowseFragment : BaseFragmentViewModel<FragmentBrowseBinding, BrowseViewModel>(
    FragmentBrowseBinding::inflate
) {
    private var mCurrentComic = -1

    private lateinit var pagerAdapter: BrowseAdapter

    private var initial = true
    override fun onInitViewBinding(savedInstanceState: Bundle?) {
        setPageAdapter()
        setSpeedDial()
        init(savedInstanceState)
        binding.expMain.retry.setOnClickListener {
            init(savedInstanceState)
        }
    }

    // check save instance
    // if null = get latest comic and load the page
    // else update page adapter with the last seen comic Id got from preferences
    private fun init(savedInstanceState: Bundle?) {
        if (null == savedInstanceState) {
            if (isNetworkConnected()) {
                exceptionLayoutGone()
                mInterceptor.setInterceptor(BASE_URL)
                viewModel.getLatestComicID()
                observeOnComic()
            } else {
                if (getPreferences().getLatestComicId() != 0) {
                    loadPageAdapter(getPreferences().getLatestComicId())
                } else {
                    exceptionLayoutVisibility()
                }
            }
        } else {
            if (isNetworkConnected()) {
                exceptionLayoutGone()
                pagerAdapter.update(getPreferences().getLatestComicId())
            } else {
                pagerAdapter.update(getPreferences().getLatestComicId())
            }
        }
    }

    private fun observeOnComic() {
        viewModel.browseComicFlowState.asLiveData().observe(this) {
            when (it.status) {
                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    getPreferences().setLatestComicId(it.data!!.num)
                    mCurrentComic = it.data!!.num
                }
                ResourceState.ERROR -> {
                    exceptionLayoutVisibility()
                    DynamicToast.makeError(
                        requireContext(),
                        it.message
                    ).show()
                }
                else -> {
                }
            }
            loadPageAdapter(mCurrentComic)
        }
    }

    // load page adapter with data ans set page position
    // also check for last seen comic id to load instead of new one
    private fun loadPageAdapter(latestComicId: Int) {
        pagerAdapter.update(latestComicId)
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mCurrentComic = position
                if (!initial) {
                    getPreferences().setLastSeenComicId(position + 1)
                }
            }
        })
        val position: Int = if (mCurrentComic != -1) mCurrentComic else latestComicId
        setPages(position)
        if (getPreferences().getLastSeenComicId() != 0) {
            val comicID = arguments?.getInt(COMIC_ID, 0)
            if (comicID != null) {
                getPreferences().setLastSeenComicId(comicID)
            }
            setPages(getPreferences().getLastSeenComicId() - 1)
            initial = false
        }
        onNewIntent()
    }

    // intent value to set page got from search and notification
    private fun onNewIntent() {
        val extras = activity?.intent?.extras
        if (extras != null) {
            initial = false
            if (extras.containsKey(NUM)) {
                getPreferences().setLatestComicId(extras.getInt(NUM))
                setPages(extras.getInt(NUM))
                activity?.intent?.removeExtra(NUM)
            }
            if (extras.containsKey(PAGE_ID)) {
                getPreferences().setLastSeenComicId(extras.getInt(PAGE_ID))
                setPages(extras.getInt(PAGE_ID) - 1)
                activity?.intent?.removeExtra(PAGE_ID)
            }
        }
    }

    // init pager adapter
    private fun setPageAdapter() {
        pagerAdapter = BrowseAdapter(this)
        binding.pager.adapter = pagerAdapter
    }

    // fab speed dial button to navigate to first , last and random page on click
    private fun setSpeedDial() {
        binding.comicSpeedDial.addOnMenuItemClickListener { fab, textView, itemId ->
            val b = when (itemId) {
                R.id.first -> {
                    setFabOnClick(FIRST_PAGE)
                    true
                }
                R.id.random -> {
                    setFabOnClick(RANDOM_PAGE)
                    true
                }
                R.id.last -> {
                    setFabOnClick(LAST_PAGE)
                    true
                }
                else -> {
                    searchIntent()
                    true
                }
            }
        }
    }

    private fun setFabOnClick(comicPage: String) {
        if (binding.pager.adapter != null) {
            val position: Int = PageSelectUtil().pageSelect(
                binding.pager.adapter!!,
                comicPage,
                getPreferences().getLastSeenComicId()
            )

            val lastSeenComicId: Int =
                if (position != -1) position else getPreferences().getLastSeenComicId()
            setPages(lastSeenComicId)
        }
    }

    private fun setPages(position: Int) {
        if (position >= 0) {
            exceptionLayoutGone()
            binding.pager.offscreenPageLimit = 1
            binding.pager.currentItem = position
        } else {
            exceptionLayoutVisibility()
        }
    }

    // on click direct to search page to search for comic Id or comic text
    private fun searchIntent() {
//        val intent = Intent(activity, SearchActivity::class.java)
//        startActivity(intent)
    }

    override fun onInitDependencyInjection() {
        DaggerBrowseComponent
            .builder()
            .coreComponent(BaseApplication.coreComponent)
            .build()
            .inject(this)
    }

    override fun onInitViewModel() {
        viewModel =
            ViewModelProvider(this, factory)
                .get(BrowseViewModel::class.java)
    }

    // Visibility status of exception and  speed dial
    private fun exceptionLayoutVisibility() {
        binding.expMain.rootExpLayout.isVisible = true
        binding.expMain.retry.isVisible = true
        binding.comicSpeedDial.isVisible = false
    }

    private fun exceptionLayoutGone() {
        binding.expMain.rootExpLayout.isVisible = false
        binding.expMain.retry.isVisible = false
        binding.comicSpeedDial.isVisible = true
    }
}
