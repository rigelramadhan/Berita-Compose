package com.rigelramadhan.beritacompose.core.domain.repository

import com.rigelramadhan.beritacompose.core.data.Resource
import com.rigelramadhan.beritacompose.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getNewsById(id: Int): Flow<Resource<News>>
    fun getFavoriteNews(): Flow<List<News>>
    fun setFavoriteNews(news: News, isFavorite: Boolean)
}