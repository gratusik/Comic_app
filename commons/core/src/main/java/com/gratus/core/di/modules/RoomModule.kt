package com.gratus.core

import android.content.Context
import androidx.room.Room
import com.gratus.core.data.local.dao.ComicDao
import com.gratus.core.data.local.db.ComicDataBase
import com.gratus.core.util.CoreConstants.DatabaseConstant.COMIC_DB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesComicDataBase(context: Context): ComicDataBase {
        return Room.databaseBuilder(
            context,
            ComicDataBase::class.java,
            COMIC_DB
        )
            .build()
    }

    @Singleton
    @Provides
    fun providesComicDao(comicDataBase: ComicDataBase): ComicDao {
        return comicDataBase.comicDao()
    }
}
