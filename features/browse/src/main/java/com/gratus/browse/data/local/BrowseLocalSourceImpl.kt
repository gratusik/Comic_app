package com.gratus.browse.data.local

import com.gratus.core.data.local.dao.ComicDao
import com.gratus.core.domain.local.ComicEntity
import javax.inject.Inject

class BrowseLocalSourceImpl @Inject constructor(
    private val comicDao: ComicDao,
) : BrowseLocalSource {
    override suspend fun insert(comicEntity: ComicEntity) {
        return comicDao.insert(comicEntity)
    }

    override suspend fun getFindComicID(comicID: Int): ComicEntity? {
        return comicDao.getFindComicID(comicID)
    }

    override suspend fun insertCheck(comicEntity: ComicEntity) {
        return comicDao.insertCheck(comicEntity)
    }

    override suspend fun updateFavouriteComic(favourite: Boolean, comicID: Int): Int {
        return comicDao.updateFavouriteComic(favourite, comicID)
    }

//    override suspend fun getAllComics(): LiveData<List<ComicEntity>> {
//        return comicDao.getAllComics()
//    }
//
//    override suspend fun getAllFavouritesComics(): LiveData<List<ComicEntity>> {
//        return comicDao.getAllFavouritesComics()
//    }
}
