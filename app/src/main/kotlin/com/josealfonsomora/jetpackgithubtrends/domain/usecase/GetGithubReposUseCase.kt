package com.josealfonsomora.jetpackgithubtrends.domain.usecase

import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository
import javax.inject.Inject

class GetGithubReposUseCase @Inject constructor(
    private val repository: GithubReposRepository
) {
    @Suppress("UNCHECKED_CAST")
    suspend fun execute(): Result {
        return when (val result = repository.getGithubRepos()) {
            is GithubReposRepository.Result.Success<*> -> Result.Success(result.data as List<GithubRepo>)
            is GithubReposRepository.Result.Error<*> -> Result.Error(result.throwable as Throwable)
        }
    }

    sealed class Result {
        data class Success(val data: List<GithubRepo>) : Result()
        data class Error(val throwable: Throwable) : Result()
    }
}
