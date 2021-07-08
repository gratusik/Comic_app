package com.gratus.core.data.cache

interface AppPrefHelper {
    fun getLastSeenComicId(): Int

    fun setLastSeenComicId(lastSeenComicId: Int)

    fun getLatestComicId(): Int

    fun setLatestComicId(latestComicId: Int)
}
