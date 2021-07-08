package com.gratus.core.util

import com.gratus.core.util.CoreConstants.CommonConstant.COMIC_ID

object CoreConstants {
    // base url
    object RemoteConstant {
        const val BASE_URL = "https://xkcd.com/"

        // get latest comic URL
        const val LATEST_URL = "info.0.json"

        // get comic based on Id URL
        const val COMIC_ID_URL = "{$COMIC_ID}/info.0.json"
    }

    object DatabaseConstant {
        // database
        const val COMIC_DB = "comic_database"
        const val COMIC_TABLE = "comic_table"
    }

    object PrefConstant {
        //  tag for shared preference constants
        const val APP_PREF_NAME = "XKCD COMICS"
        const val LAST_SEEN_COMIC_ID = "last_seen_comic_Id"
        const val LATEST_COMIC_ID = "latest_comic_Id"
    }

    object CommonConstant {
        const val COMIC_ID = "comicId"
    }

    object UIConstant {
        const val NETWORK_AVAILABLE_ACTION = "NW AVAILABLE"
        const val IS_NETWORK_AVAILABLE = "isNetworkAvailable"
    }

    object BrowseConstants {
        // page selection constants used main activity
        const val LAST_PAGE = "LAST_PAGE"
        const val FIRST_PAGE = "FIRST_PAGE"
        const val RANDOM_PAGE = "RANDOM_PAGE"
    }

    object IntentConstants {
        //  tag for intent constants used in search and on click from list fragment
        const val NUM = "num"
        const val PAGE_ID = "pageId"
    }
}
