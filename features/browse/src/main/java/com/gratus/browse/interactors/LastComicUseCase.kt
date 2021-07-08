package com.gratus.browse.interactors

import com.gratus.browse.domain.repository.BrowseRepository
import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.network.Resource
import kotlinx.coroutines.flow.Flow

class LastComicUseCase(private val repo: BrowseRepository) {

    suspend fun execute(): Flow<Resource<ComicResponse>> {
        return repo.getLatestComicID()
    }
}
