package com.josealfonsomora.jetpackgithubtrends.domain.usecase

import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository
import javax.inject.Inject

class GetGithubRepoUseCase @Inject constructor(
    private val repository: GithubReposRepository
) {
    suspend fun execute(repoId: Int): Result {
        return when (val result = repository.getGithubRepo(repoId)) {
            is GithubReposRepository.Result.Success<*> -> Result.Success(result.data as GithubRepo)
            is GithubReposRepository.Result.Error<*> -> Result.Error(result.throwable as Throwable)
        }
    }

    sealed class Result {
        data class Success(val data: GithubRepo) : Result()
        data class Error(val throwable: Throwable) : Result()
    }
}
