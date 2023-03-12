package com.rigelramadhan.beritacompose.core.data.source.local.room

import androidx.room.*
import com.rigelramadhan.beritacompose.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNewsById(id: Int): Flow<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Query("SELECT * FROM news WHERE isFavorite = 1")
    fun getFavoriteNews(): Flow<List<NewsEntity>>

    @Update
    fun updateFavoriteNews(news: NewsEntity)
}