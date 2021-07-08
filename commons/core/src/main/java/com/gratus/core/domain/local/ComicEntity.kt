package com.gratus.core.domain.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gratus.core.util.CoreConstants.DatabaseConstant.COMIC_TABLE

@Entity(tableName = COMIC_TABLE)
// model data class for comic room to store comic details
data class ComicEntity(
    val month: String,
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    val safeTitle: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
    val isFavourite: Int,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
)
