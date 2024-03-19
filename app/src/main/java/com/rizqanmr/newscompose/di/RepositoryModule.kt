package com.rizqanmr.newscompose.di

import com.rizqanmr.newscompose.repository.NewsRepository
import com.rizqanmr.newscompose.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepo(newsRepository: NewsRepositoryImpl): NewsRepository
}