package com.josealfonsomora.jetpackgithubtrends.data.di

import com.josealfonsomora.jetpackgithubtrends.data.network.GithubApi
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.AppDatabase
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.GithubReposDb
import com.josealfonsomora.jetpackgithubtrends.data.repository.GithubReposRepositoryImp
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class DataRepositoryModule {

    @Provides
    fun provideGithubReposRepository(githubApi: GithubApi, database: AppDatabase): GithubReposRepository =
        GithubReposRepositoryImp(githubApi, database)
}
