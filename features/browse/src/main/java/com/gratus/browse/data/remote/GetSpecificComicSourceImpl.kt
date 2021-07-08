package com.gratus.browse.data.remote

import com.gratus.core.data.remote.api.ComicIDService
import com.gratus.core.domain.remote.ComicResponse
import javax.inject.Inject

// data source which hits the service api
class GetSpecificComicSourceImpl @Inject constructor(
    private var comicIDService: ComicIDService
) : GetSpecificComicSource {
    override suspend fun getSpecificComic(comicID: Int): ComicResponse {
        return comicIDService.getComicID(comicID)
    }
}
