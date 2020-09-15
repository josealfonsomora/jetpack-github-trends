package com.josealfonsomora.jetpackgithubtrends.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.threeten.bp.ZoneId
import javax.inject.Qualifier

@Module
@InstallIn(ApplicationComponent::class)
object CoreModule {
    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @SystemZoneId
    @Provides
    fun providesSystemZoneId(): ZoneId = ZoneId.systemDefault()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SystemZoneId