package com.rigelramadhan.beritacompose.core.domain.usecase

import com.rigelramadhan.beritacompose.core.data.Resource
import com.rigelramadhan.beritacompose.core.domain.model.News
import com.rigelramadhan.beritacompose.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: INewsRepository) : NewsUseCase {
    override fun getAllNews(): Flow<Resource<List<News>>> = newsRepository.getAllNews()

    override fun getNewsById(id: Int): Flow<Resource<News>> = newsRepository.getNewsById(id)

    override fun getFavoriteNews(): Flow<List<News>> = newsRepository.getFavoriteNews()

    override fun setFavoriteNews(news: News, isFavorite: Boolean) =
        newsRepository.setFavoriteNews(news, isFavorite)
}