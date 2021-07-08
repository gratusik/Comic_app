package com.gratus.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gratus.core.data.local.dao.ComicDao
import com.gratus.core.domain.local.ComicEntity

// Database setup and version
@Database(entities = [ComicEntity::class], version = 1)
abstract class ComicDataBase : RoomDatabase() {

    abstract fun comicDao(): ComicDao
}
