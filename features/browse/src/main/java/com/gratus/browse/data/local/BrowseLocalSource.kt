package com.gratus.browse.data.local

import com.gratus.core.domain.local.ComicEntity

interface BrowseLocalSource {
    suspend fun insert(comicEntity: ComicEntity)
    suspend fun getFindComicID(comicID: Int): ComicEntity?
    suspend fun updateFavouriteComic(favourite: Boolean, comicID: Int): Int

    //    suspend fun getAllComics(): LiveData<List<ComicEntity>>
//    suspend fun getAllFavouritesComics(): LiveData<List<ComicEntity>>
    suspend fun insertCheck(comicEntity: ComicEntity)
}
