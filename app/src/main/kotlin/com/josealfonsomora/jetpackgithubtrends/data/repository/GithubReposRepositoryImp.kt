package com.josealfonsomora.jetpackgithubtrends.data.repository

import com.josealfonsomora.jetpackgithubtrends.data.model.toDomainModel
import com.josealfonsomora.jetpackgithubtrends.data.network.GithubApi
import com.josealfonsomora.jetpackgithubtrends.domain.exceptions.EmptyContentException
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository

class GithubReposRepositoryImp(
    private val githubApi: GithubApi
) : GithubReposRepository {

    override suspend fun getGithubRepos() = try {
        val response = githubApi.getRepositories()
        if (response.isSuccessful) {
            response.body()?.items?.let { list ->
                GithubReposRepository.Result.Success(list.map { it.toDomainModel() })
            } ?: kotlin.run {
                GithubReposRepository.Result.Error(EmptyContentException("Items is null"))
            }
        } else {
            GithubReposRepository.Result.Error(Throwable(response.errorBody().toString()))
        }
    } catch (e: Throwable) {
        GithubReposRepository.Result.Error(e)
    }
}
