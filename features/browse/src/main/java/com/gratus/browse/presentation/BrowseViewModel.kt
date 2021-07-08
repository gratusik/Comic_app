package com.gratus.browse.presentation

import androidx.lifecycle.viewModelScope
import com.gratus.browse.interactors.GetSpecificComicUseCase
import com.gratus.browse.interactors.LastComicUseCase
import com.gratus.browse.interactors.LocalCacheUseCase
import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.network.Resource
import com.gratus.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class BrowseViewModel @Inject constructor(
    private var latestComicUseCase: LastComicUseCase,
    private var getSpecificComicUseCase: GetSpecificComicUseCase,
    private var localCacheUseCase: LocalCacheUseCase,
) : BaseViewModel() {
    var browseComicFlowState: MutableStateFlow<Resource<ComicResponse>> =
        MutableStateFlow(Resource.none())
    var specificComicFlowState: MutableStateFlow<Resource<ComicResponse>> =
        MutableStateFlow(Resource.none())

    // Checking latest comic ID api to comic response
    fun getLatestComicID() {
        browseComicFlowState.value = Resource.loading()
        viewModelScope.launch {
            latestComicUseCase.execute().collect {
                browseComicFlowState.value = it
            }
        }
    }

    fun getSpecificComic(comicID: Int) {
        specificComicFlowState.value = Resource.loading()
        viewModelScope.launch {
            getSpecificComicUseCase.execute(comicID).collect {
                specificComicFlowState.value = it
            }
        }
    }

    fun updateFavouriteComic(isFavourite: Boolean, comicID: Int) {
        browseComicFlowState.value = Resource.loading()
        viewModelScope.launch {
            localCacheUseCase.execute(isFavourite, comicID)
        }
    }
}
