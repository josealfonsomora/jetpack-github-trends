package com.josealfonsomora.jetpackgithubtrends.domain.repository

interface GithubReposRepository {

    suspend fun getGithubRepos(): Result

    suspend fun getGithubRepo(repoId: Int): Result

    sealed class Result {
        class Success<T>(val data: T) : Result()
        class Error<T>(val throwable: T) : Result()
    }
}
