package com.rigelramadhan.beritacompose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsItem(

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

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)