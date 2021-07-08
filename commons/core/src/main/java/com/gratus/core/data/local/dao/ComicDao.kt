@file:Suppress("SpellCheckingInspection")

package com.gratus.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gratus.core.domain.local.ComicEntity

// DAO for room datatbase
@Dao
interface ComicDao {
    // insert dao new comics
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comicEntity: ComicEntity)

    // searched results of comic on type in edit text using comicId
    @Query("SELECT * FROM comic_table WHERE num LIKE '%' || :comicID || '%' order by num DESC LIMIT 10")
    fun getSearchComicID(comicID: Int): LiveData<List<ComicEntity>>

    // searched results of comic on type in edit text using comic title text
    @Query("SELECT * FROM comic_table WHERE safeTitle LIKE '%' || :safeTitle || '%' order by safeTitle DESC LIMIT 10")
    fun getSearchComicTitle(safeTitle: String): LiveData<List<ComicEntity>>

    // find based on  comic ID on browsing of comics
    @Query("SELECT * FROM comic_table WHERE num = :comicID")
    fun getFindComicID(comicID: Int): ComicEntity?

    // find based on  comic ID on browsing of comics
    @Query("SELECT * FROM comic_table WHERE num = :comicID")
    fun getCheckComicID(comicID: Int): List<ComicEntity>

    // update favourite comic with comic ID
    @Query("Update comic_table SET isFavourite= :favourite WHERE num = :comicID")
    fun updateFavouriteComic(favourite: Boolean, comicID: Int): Int

    // get all comic in offline
    @Query("SELECT * from comic_table ORDER BY num DESC")
    fun getAllComics(): LiveData<List<ComicEntity>>

    // get all favourite comic in offline
    @Query("SELECT * from comic_table where isFavourite = 1 ORDER BY num DESC")
    fun getAllFavouritesComics(): LiveData<List<ComicEntity>>

    fun insertCheck(comicEntity: ComicEntity) {
        val itemsFromDB: List<ComicEntity>? = comicEntity.num?.let { getCheckComicID(it) }
        if (itemsFromDB?.isEmpty() == true) {
            insert(comicEntity)
        }
    }
}
