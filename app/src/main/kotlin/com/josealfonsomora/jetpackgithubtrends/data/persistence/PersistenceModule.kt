package com.josealfonsomora.jetpackgithubtrends.data.persistence

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.GithubReposDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GithubReposDb =
        Room.databaseBuilder(
            context,
            GithubReposDb::class.java, "githubrepos.db"
        ).build()

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.createDataStore(name = "cache")
}
