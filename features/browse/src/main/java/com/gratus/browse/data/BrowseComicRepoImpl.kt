package com.gratus.browse.data

import com.gratus.browse.data.local.BrowseLocalSourceImpl
import com.gratus.browse.data.remote.GetLatestComicIDSourceImpl
import com.gratus.browse.data.remote.GetSpecificComicSourceImpl
import com.gratus.browse.domain.repository.BrowseRepository
import com.gratus.core.data.local.mapper.ComicToEntityMapper
import com.gratus.core.data.local.mapper.EntityToComicMapper
import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.network.NetworkBoundResource
import com.gratus.core.util.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BrowseComicRepoImpl(
    private val getLatestComicIDSourceImpl: GetLatestComicIDSourceImpl,
    private val getSpecificComicSourceImpl: GetSpecificComicSourceImpl,
    private val browseLocalSourceImpl: BrowseLocalSourceImpl
) : BrowseRepository {
    override suspend fun getLatestComicID(): Flow<Resource<ComicResponse>> {
        return object : NetworkBoundResource<ComicResponse>() {
            var localResponse: ComicResponse? = null
            override suspend fun remoteFetch(): ComicResponse {
                return getLatestComicIDSourceImpl.getLastComicID()
            }

            override suspend fun localFetch(): ComicResponse? {
                return localResponse
            }

            override fun shouldFetchWithLocalData() = false
            override suspend fun saveFetchResult(data: ComicResponse) {
            }
        }.asFlow()
    }

    override suspend fun getSpecificComic(comicID: Int): Flow<Resource<ComicResponse>> {
        return object : NetworkBoundResource<ComicResponse>() {
            var localResponse: ComicResponse? = null
            override suspend fun remoteFetch(): ComicResponse {
                return getSpecificComicSourceImpl.getSpecificComic(comicID)
            }

            override suspend fun localFetch(): ComicResponse? {
                withContext(Dispatchers.IO) {
                    localResponse =
                        browseLocalSourceImpl.getFindComicID(comicID)?.let {
                            EntityToComicMapper().map(
                                it
                            )
                        }
                }
                return localResponse
            }

            override fun shouldFetchWithLocalData() = true
            override suspend fun saveFetchResult(data: ComicResponse) {
                withContext(Dispatchers.IO) {
                    data.isFavourite = localResponse?.isFavourite ?: 0
                    browseLocalSourceImpl.insertCheck(ComicToEntityMapper().map(data))
                }
            }
        }.asFlow()
    }

    override suspend fun updateFavoriteComic(
        isFavourite: Boolean,
        comicID: Int
    ) {
        withContext(Dispatchers.IO) {
            browseLocalSourceImpl.updateFavouriteComic(isFavourite, comicID)
        }
    }
}
