package com.gratus.core.data.remote.api

import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.CoreConstants.CommonConstant.COMIC_ID
import com.gratus.core.util.CoreConstants.RemoteConstant.COMIC_ID_URL
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicIDService {
    @GET(COMIC_ID_URL)
    suspend fun getComicID(@Path(COMIC_ID) comicId: Int): ComicResponse
}
