package com.josealfonsomora.jetpackgithubtrends.domain.usecase

import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepository
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository
import javax.inject.Inject

class GetGithubReposUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    @Suppress("UNCHECKED_CAST")
    suspend fun execute(): Result {
        return when (val result = githubReposRepository.getGithubRepos()) {
            is GithubReposRepository.Result.Success<*> -> Result.Success(result.data as List<GithubRepository>)
            is GithubReposRepository.Result.Error<*> -> Result.Error(result.throwable as Throwable)
        }
    }

    sealed class Result {
        data class Success(val data: List<GithubRepository>) : Result()
        data class Error(val throwable: Throwable) : Result()
    }
}
