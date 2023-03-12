package com.rigelramadhan.beritacompose.core.data.source.local

import com.rigelramadhan.beritacompose.core.data.source.local.entity.NewsEntity
import com.rigelramadhan.beritacompose.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {
    fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

    fun getNewsById(id: Int): Flow<NewsEntity> = newsDao.getNewsById(id)

    suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

    fun getFavoriteNews(): Flow<List<NewsEntity>> = newsDao.getFavoriteNews()

    fun setFavoriteNews(news: NewsEntity, isFavorite: Boolean) {
        news.isFavorite = isFavorite
        newsDao.updateFavoriteNews(news)
    }
}