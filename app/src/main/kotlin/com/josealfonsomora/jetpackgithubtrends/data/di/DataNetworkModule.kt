package com.josealfonsomora.jetpackgithubtrends.data.di

import com.google.gson.GsonBuilder
import com.josealfonsomora.jetpackgithubtrends.BuildConfig
import com.josealfonsomora.jetpackgithubtrends.data.network.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
class DataNetworkModule {
    @Provides
    fun provideOkhttpLogginInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.BUILD_TYPE == "release") {
            HttpLoggingInterceptor.Level.NONE
        } else {
            HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                newRequest.addHeader("accept", "application/vnd.github.v3+json")
                chain.proceed(newRequest.build())
            }

        return builder.build()
    }

    @Provides
    fun providesConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder().create()
        return GsonConverterFactory.create(gsonBuilder)
    }

    @Provides
    fun provideBaby2BodyApi(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): GithubApi =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("https://api.github.com/")
            .build().create(GithubApi::class.java)
}
