package com.gratus.browse.util

import androidx.recyclerview.widget.RecyclerView
import com.gratus.core.util.CoreConstants.BrowseConstants.FIRST_PAGE
import com.gratus.core.util.CoreConstants.BrowseConstants.LAST_PAGE
import com.gratus.core.util.CoreConstants.BrowseConstants.RANDOM_PAGE
import com.gratus.core.util.CoreConstants.PrefConstant.LAST_SEEN_COMIC_ID
import kotlin.random.Random

// to set comic page in random,first,last and last seen comic on click of fab 
class PageSelectUtil {
    fun pageSelect(adapter: RecyclerView.Adapter<*>, comicPage: String, lastSeenComicId: Int): Int {
        var comicId = -1
        when (comicPage) {
            FIRST_PAGE -> {
                comicId = 0
            }
            RANDOM_PAGE -> {
                comicId = Random.nextInt(adapter.itemCount)
            }
            LAST_PAGE -> {
                comicId = adapter.itemCount - 1
            }
            LAST_SEEN_COMIC_ID -> {
                comicId = lastSeenComicId - 1
            }
        }
        return comicId
    }
}
