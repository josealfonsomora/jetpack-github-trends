package com.josealfonsomora.jetpackgithubtrends.data.persistence.database

import com.josealfonsomora.jetpackgithubtrends.data.model.GithubRepoDataModel
import com.josealfonsomora.jetpackgithubtrends.data.model.toDatabaseEntity
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.toDomainModel
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import javax.inject.Inject

class AppDatabase @Inject constructor(val database: GithubReposDb) {

    suspend fun getAllGithubRepos(): List<GithubRepo> = try {
        val licensesEntities = database.githubLicenseDao().getAll()
        val licenses = licensesEntities.map { it.toDomainModel() }

        val ownersEntities = database.githubOwnerDao().getAll()
        val owners = ownersEntities.map { it.toDomainModel() }

        val reposEntities = database.githubRepoDao().getAll()
        reposEntities.map { entity ->
            entity.toDomainModel(
                owners.firstOrNull { it.id == entity.owner },
                licenses.firstOrNull { it.key == entity.license })
        }
    } catch (e: Throwable) {
        emptyList()
    }

    suspend fun saveGithubRepos(list: List<GithubRepoDataModel>) {
        database.githubRepoDao().insertAll(list.map { it.toDatabaseEntity() })
        database.githubOwnerDao()
            .insertAll(list.filter { it.owner != null }.map { it.owner!!.toDatabaseEntity() })
        database.githubLicenseDao()
            .insertAll(list.filter { it.license != null }.map { it.license!!.toDatabaseEntity() })
    }

}
