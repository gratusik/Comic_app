package com.gratus.browse.interactors

import com.gratus.browse.domain.repository.BrowseRepository

class LocalCacheUseCase(private val repo: BrowseRepository) {

    suspend fun execute(isFavourite: Boolean, comicID: Int) {
        return repo.updateFavoriteComic(isFavourite, comicID)
    }
}
