package com.rigelramadhan.beritacompose.core.utils

import com.rigelramadhan.beritacompose.core.data.source.local.entity.NewsEntity
import com.rigelramadhan.beritacompose.core.data.source.remote.response.NewsItem
import com.rigelramadhan.beritacompose.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<NewsItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                summary = it.summary,
                featured = it.featured,
                publishedAt = it.publishedAt,
                imageUrl = it.imageUrl,
                newsSite = it.newsSite,
                id = it.id,
                title = it.title,
                url = it.url,
                updatedAt = it.updatedAt,
                isFavorite = false,
            )
            newsList.add(news)
        }

        return newsList
    }

    fun mapEntityToDomain(input: NewsEntity) = News(
        summary = input.summary,
        featured = input.featured,
        publishedAt = input.publishedAt,
        imageUrl = input.imageUrl,
        newsSite = input.newsSite,
        id = input.id,
        title = input.title,
        url = input.url,
        updatedAt = input.updatedAt,
        isFavorite = false,
    )

    fun mapResponseToEntity(input: NewsItem) = NewsEntity(
        summary = input.summary,
        featured = input.featured,
        publishedAt = input.publishedAt,
        imageUrl = input.imageUrl,
        newsSite = input.newsSite,
        id = input.id,
        title = input.title,
        url = input.url,
        updatedAt = input.updatedAt,
        isFavorite = false,
    )

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> = input.map {
        News(
            summary = it.summary,
            featured = it.featured,
            publishedAt = it.publishedAt,
            imageUrl = it.imageUrl,
            newsSite = it.newsSite,
            id = it.id,
            title = it.title,
            url = it.url,
            updatedAt = it.updatedAt,
            isFavorite = it.isFavorite,
        )
    }

    fun mapDomainToEntity(input: News) = NewsEntity(
        summary = input.summary,
        featured = input.featured,
        publishedAt = input.publishedAt,
        imageUrl = input.imageUrl,
        newsSite = input.newsSite,
        id = input.id,
        title = input.title,
        url = input.url,
        updatedAt = input.updatedAt,
        isFavorite = input.isFavorite,
    )
}