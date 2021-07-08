package com.gratus.browse.util

import com.gratus.core.domain.remote.ComicResponse
import com.gratus.core.util.CoreConstants.RemoteConstant.BASE_URL

class ConcatUtil {
    // concat string for comic card
    fun convertIntoForTitle(comicResponse: ComicResponse): String {
        if (comicResponse.day?.toInt()!! < 10) {
            comicResponse.day = "0" + comicResponse.day
        }
        if (comicResponse.month?.toInt()!! < 10) {
            comicResponse.month = "0" + comicResponse.month
        }
        val comicDate: String =
            comicResponse.day + "-" + comicResponse.month + "-" + comicResponse.year
        val comicID: String = "#" + comicResponse.num
        val comicLink: String = BASE_URL + comicResponse.num + "/"
        return "$comicID $comicDate\n$comicLink"
    }

    // concat string to get url and share text
    fun concatShareString(string: String, title: String?, num: Int): String {
        return "$string $title - $BASE_URL$num/"
    }

    // concat string used in notification text
    fun concatNotifyString(title: String, num: Int): String {
        return "$title - $num"
    }
}
