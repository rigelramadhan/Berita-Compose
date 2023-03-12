package com.rigelramadhan.beritacompose.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(

	@field:SerializedName("NewsListResponse")
	val data: List<NewsItem>
)