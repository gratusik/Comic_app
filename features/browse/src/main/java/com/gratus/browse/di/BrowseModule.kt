package com.gratus.browse.di

import com.gratus.browse.data.BrowseComicRepoImpl
import com.gratus.browse.data.local.BrowseLocalSourceImpl
import com.gratus.browse.data.remote.GetLatestComicIDSourceImpl
import com.gratus.browse.data.remote.GetSpecificComicSourceImpl
import com.gratus.browse.domain.repository.BrowseRepository
import com.gratus.browse.interactors.GetSpecificComicUseCase
import com.gratus.browse.interactors.LastComicUseCase
import com.gratus.browse.interactors.LocalCacheUseCase
import com.gratus.core.data.local.dao.ComicDao
import com.gratus.core.data.local.db.ComicDataBase
import com.gratus.core.data.remote.api.ComicIDService
import com.gratus.core.data.remote.api.LatestComicService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [BrowseFeatureModule::class])
class BrowseModule {
    @Provides
    fun provideLatestComicService(retrofit: Retrofit): LatestComicService {
        return retrofit.create(LatestComicService::class.java)
    }

    @Provides
    fun provideGetSpecificComicService(retrofit: Retrofit): ComicIDService {
        return retrofit.create(ComicIDService::class.java)
    }

    // repo provider
    @Provides
    fun provideBrowserRepository(
        getLatestComicIDSourceImpl: GetLatestComicIDSourceImpl,
        getSpecificComicSourceImpl: GetSpecificComicSourceImpl,
        browseLocalSourceImpl: BrowseLocalSourceImpl
    ): BrowseRepository {
        return BrowseComicRepoImpl(
            getLatestComicIDSourceImpl,
            getSpecificComicSourceImpl,
            browseLocalSourceImpl
        )
    }

    @Provides
    fun providesLastComicUseCase(browseRepository: BrowseRepository): LastComicUseCase {
        return LastComicUseCase(browseRepository)
    }

    @Provides
    fun providesGetSpecificComicUseCase(browseRepository: BrowseRepository): GetSpecificComicUseCase {
        return GetSpecificComicUseCase(browseRepository)
    }

    @Provides
    fun providesLocalCacheUseCase(browseRepository: BrowseRepository): LocalCacheUseCase {
        return LocalCacheUseCase(browseRepository)
    }
}
