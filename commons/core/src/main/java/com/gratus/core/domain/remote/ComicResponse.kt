package com.gratus.core.domain.remote

import com.google.gson.annotations.SerializedName

// comic response from xkcd online both latest comic and load comic based on comic Id
data class ComicResponse(
    @SerializedName("month")
    var month: String,
    @SerializedName("num")
    var num: Int,
    @SerializedName("link")
    var link: String,
    @SerializedName("year")
    var year: String,
    @SerializedName("news")
    var news: String,
    @SerializedName("safe_title")
    var safeTitle: String,
    @SerializedName("transcript")
    var transcript: String,
    @SerializedName("alt")
    var alt: String,
    @SerializedName("img")
    var img: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("day")
    var day: String,
    var isFavourite: Int = 0
)