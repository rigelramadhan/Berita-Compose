package com.rigelramadhan.beritacompose.core.di

import com.rigelramadhan.beritacompose.core.data.NewsRepository
import com.rigelramadhan.beritacompose.core.domain.repository.INewsRepository
import com.rigelramadhan.beritacompose.core.domain.usecase.NewsInteractor
import com.rigelramadhan.beritacompose.core.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(repository: NewsRepository): INewsRepository = repository

    @Provides
    fun provideNewsUseCase(repository: INewsRepository): NewsUseCase {
        return NewsInteractor(repository)
    }
}