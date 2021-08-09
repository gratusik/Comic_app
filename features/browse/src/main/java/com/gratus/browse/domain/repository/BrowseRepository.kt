package com.gratus.browse.domain.repository

import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.network.Resource
import kotlinx.coroutines.flow.Flow

// repo to getLatest Comic ID
interface BrowseRepository {
    suspend fun getLatestComicID(): Flow<Resource<ComicResponse>>
    suspend fun getSpecificComic(comicID: Int): Flow<Resource<ComicResponse>>
    suspend fun updateFavoriteComic(isFavourite: Boolean, comicID: Int)
}
