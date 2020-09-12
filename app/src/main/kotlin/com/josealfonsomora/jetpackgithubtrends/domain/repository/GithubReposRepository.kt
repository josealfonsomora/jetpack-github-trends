package com.josealfonsomora.jetpackgithubtrends.domain.repository

import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepository

internal interface GithubReposRepository {
    suspend fun getGithubRepos(): List<GithubRepository>
}
