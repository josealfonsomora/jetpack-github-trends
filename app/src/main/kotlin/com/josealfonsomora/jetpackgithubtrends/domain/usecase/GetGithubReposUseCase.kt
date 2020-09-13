package com.josealfonsomora.jetpackgithubtrends.domain.usecase

import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepository
import javax.inject.Inject

class GetGithubReposUseCase @Inject constructor() {
    suspend fun execute(): Result {
        Thread.sleep(4000)
        return Result.Success(
            listOf(GithubRepository(name = "Name"), GithubRepository(name = "Name 2"))
        )
    }

    sealed class Result {
        data class Success(val data: List<GithubRepository>) : Result()
        data class Error(val e: Throwable) : Result()
    }
}