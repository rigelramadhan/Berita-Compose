package com.rigelramadhan.beritacompose.core.data.source.remote

import com.rigelramadhan.beritacompose.core.data.source.remote.network.ApiResponse
import com.rigelramadhan.beritacompose.core.data.source.remote.network.ApiService
import com.rigelramadhan.beritacompose.core.data.source.remote.response.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getAllNews(): Flow<ApiResponse<List<NewsItem>>> {
        return flow {
            try {
                val response = apiService.getNews()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getNewsById(id: Int): Flow<ApiResponse<NewsItem>> {
        return flow {
            try {
                val response = apiService.getNewsById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}