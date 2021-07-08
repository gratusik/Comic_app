package com.gratus.core.data.remote.api

import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.CoreConstants.RemoteConstant.LATEST_URL
import retrofit2.http.GET

interface LatestComicService {
    @GET(LATEST_URL)
    suspend fun getLatestComic(): ComicResponse
}
