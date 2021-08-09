package com.gratus.browse.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.gratus.browse.R
import com.gratus.browse.databinding.FragmentBrowseCardBinding
import com.gratus.browse.di.DaggerBrowseComponent
import com.gratus.browse.util.ConcatUtil
import com.gratus.core.BaseApplication
import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.CoreConstants.IntentConstants.NUM
import com.gratus.core.util.CoreConstants.RemoteConstant.BASE_URL
import com.gratus.core.util.network.ResourceState
import com.gratus.ui.base.BaseFragmentViewModel
import com.gratus.ui.util.ImageLoaderUtil

class BrowseCardFragment :
    BaseFragmentViewModel<FragmentBrowseCardBinding, BrowseViewModel>(FragmentBrowseCardBinding::inflate) {
//    @Inject
//    lateinit var intentDispatcher: IntentDispatcher

    companion object {

        fun newInstance(comicNum: Int): BrowseCardFragment {
            val fragment = BrowseCardFragment()
            val bundle = Bundle().apply {
                putInt(NUM, comicNum)
            }
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(): BrowseCardFragment {
            return BrowseCardFragment()
        }
    }

    override fun onInitViewBinding(savedInstanceState: Bundle?) {
        init()
        binding.expCard.retry.setOnClickListener {
            init()
        }
    }

    private fun init() {
        if (arguments?.getInt(NUM) != null && arguments?.getInt(NUM) != -1) {
            mInterceptor.setInterceptor(BASE_URL)
            arguments?.getInt(NUM)?.let { viewModel.getSpecificComic(it) }
            observeSpecificComic()
        } else {
            exceptionLayoutVisibility()
        }
    }

    // get comic from offline room db using comicId
    // if present load the data
    // else get data from online to load the page
    private fun observeSpecificComic() {
        viewModel.specificComicFlowState.asLiveData().observe(this) {
            when (it.status) {
                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    setPage(it.data)
                }
                ResourceState.ERROR -> {
                    exceptionLayoutVisibility()
                }
                else -> {
                }
            }
        }
    }

    // setting the page with comic response
    private fun setPage(comicResponse: ComicResponse?) {
        if (comicResponse != null) {
            ImageLoaderUtil().loadGlideIntoImageView(
                comicResponse.img,
                binding.imageView
            )
            binding.titleText.text = comicResponse.title
            binding.subtitleText.text = comicResponse.alt
            binding.infoText.text = ConcatUtil().convertIntoForTitle(
                comicResponse
            )
            if (comicResponse.isFavourite == 1) {
                binding.iconFavourite.isChecked = true
            }
            binding.iconFavourite.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.updateFavouriteComic(isChecked, comicResponse.num)
            }
            binding.imageView.setOnClickListener {
                ImageLoaderUtil().showFrescoImage(
                    binding.imageView,
                    comicResponse.img,
                    R.dimen.margin_16dp
                )
            }
            binding.shareImageView.setOnClickListener {
                // intentDispatcher.share(requireActivity(), comicResponse)
            }
            binding.expCard.rootExpLayout.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.cardLayout.visibility = View.VISIBLE
        } else {
            exceptionLayoutVisibility()
        }
    }

    // visibility of exception when  no internet connection or offline data
    private fun exceptionLayoutVisibility() {
        binding.cardLayout.isVisible = false
        binding.progressBar.isVisible = false
        binding.expCard.rootExpLayout.isVisible = true
        binding.expCard.retry.isVisible = true
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
}
