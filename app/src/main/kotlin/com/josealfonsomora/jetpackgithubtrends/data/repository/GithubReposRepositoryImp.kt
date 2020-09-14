package com.josealfonsomora.jetpackgithubtrends.data.repository

import com.josealfonsomora.jetpackgithubtrends.data.model.toDomainModel
import com.josealfonsomora.jetpackgithubtrends.data.network.GithubApi
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.AppDatabase
import com.josealfonsomora.jetpackgithubtrends.domain.exceptions.EmptyContentException
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository

class GithubReposRepositoryImp(
    private val githubApi: GithubApi,
    private val database: AppDatabase
) : GithubReposRepository {

    override suspend fun getGithubRepos() = try {
        val savedData = database.getAllGithubRepos()
        if (savedData.isNotEmpty()) {
            GithubReposRepository.Result.Success(savedData)
        } else {
            val response = githubApi.getRepositories()
            if (response.isSuccessful) {
                response.body()?.items?.let { list ->
                    database.saveGithubRepos(list)
                    GithubReposRepository.Result.Success(list.map { it.toDomainModel() })
                } ?: run {
                    GithubReposRepository.Result.Error(EmptyContentException("Items are null"))
                }
            } else {
                GithubReposRepository.Result.Error(Throwable(response.errorBody().toString()))
            }
        }
    } catch (e: Throwable) {
        GithubReposRepository.Result.Error(e)
    }
}
