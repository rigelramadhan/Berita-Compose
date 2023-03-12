package com.rigelramadhan.beritacompose.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "news")
data class NewsEntity(
    @field:SerializedName("summary")
    val summary: String,

    @field:SerializedName("featured")
    val featured: Boolean,

    @field:SerializedName("publishedAt")
    val publishedAt: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("newsSite")
    val newsSite: String,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String,

    @field:SerializedName("isFavorite")
    var isFavorite: Boolean
) : Parcelable
