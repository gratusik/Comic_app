package com.gratus.core.data.local.mapper

import com.gratus.core.domain.local.ComicEntity
import com.gratus.core.domain.remote.ComicResponse

class ComicToEntityMapper : BaseMapper<ComicResponse, ComicEntity>() {
    override fun map(value: ComicResponse): ComicEntity {
        value.run {
            return ComicEntity(
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
