package com.gratus.browse.data.remote

import com.gratus.core.data.remote.api.LatestComicService
import com.gratus.core.domain.remote.ComicResponse
import javax.inject.Inject

// data source which hits the service api
class GetLatestComicIDSourceImpl @Inject constructor(
    private var latestComicService: LatestComicService
) : GetLatestComicIDSource {

    override suspend fun getLastComicID(): ComicResponse {
        return latestComicService.getLatestComic()
    }
}
