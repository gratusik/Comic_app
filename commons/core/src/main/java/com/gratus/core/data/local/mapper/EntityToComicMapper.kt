package com.gratus.core.data.local.mapper

import com.gratus.core.domain.local.ComicEntity
import com.gratus.core.domain.remote.ComicResponse

class EntityToComicMapper : BaseMapper<ComicEntity, ComicResponse>() {
    override fun map(value: ComicEntity): ComicResponse {
        value?.run {
            return ComicResponse(
                num = num,
                month = month,
                link = link,
                year = year,
                day = day,
                news = news,
                safeTitle = safeTitle,
                transcript = transcript,
                alt = alt,
                img = img,
                title = title,
                isFavourite = isFavourite
            )
        }
    }
}
