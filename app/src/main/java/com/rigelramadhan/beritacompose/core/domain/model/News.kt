package com.rigelramadhan.beritacompose.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val summary: String,
    val featured: Boolean,
    val publishedAt: String,
    val imageUrl: String,
    val newsSite: String,
    val id: Int,
    val title: String,
    val url: String,
    val updatedAt: String,
    var isFavorite: Boolean
) : Parcelable
