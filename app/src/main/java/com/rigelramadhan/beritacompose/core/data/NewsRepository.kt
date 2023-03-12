package com.rigelramadhan.beritacompose.core.data

import com.rigelramadhan.beritacompose.core.data.source.local.LocalDataSource
import com.rigelramadhan.beritacompose.core.data.source.remote.RemoteDataSource
import com.rigelramadhan.beritacompose.core.data.source.remote.network.ApiResponse
import com.rigelramadhan.beritacompose.core.data.source.remote.response.NewsItem
import com.rigelramadhan.beritacompose.core.domain.model.News
import com.rigelramadhan.beritacompose.core.domain.repository.INewsRepository
import com.rigelramadhan.beritacompose.core.utils.AppExecutors
import com.rigelramadhan.beritacompose.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<News>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<NewsItem>>> {
                return remoteDataSource.getAllNews()
            }

            override suspend fun saveCallResult(data: List<NewsItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getNewsById(id: Int): Flow<Resource<News>> =
        object : NetworkBoundResource<News, NewsItem>(appExecutors) {
            override fun loadFromDB(): Flow<News> {
                return localDataSource.getNewsById(id).map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: News?): Boolean = data?.isFavorite == false

            override suspend fun createCall(): Flow<ApiResponse<NewsItem>> {
                return remoteDataSource.getNewsById(id)
            }

            override suspend fun saveCallResult(data: NewsItem) {
                val news = DataMapper.mapResponseToEntity(data)
                localDataSource.insertNews(listOf(news))
            }
        }.asFlow()

    override fun getFavoriteNews(): Flow<List<News>> {
        return localDataSource.getFavoriteNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteNews(news: News, isFavorite: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteNews(newsEntity, isFavorite)
        }
    }
}