package com.gratus.browse.data.remote

import com.gratus.core.domain.remote.ComicResponse

// data source which hits the service api
interface GetSpecificComicSource {
    suspend fun getSpecificComic(comicID: Int): ComicResponse
}
