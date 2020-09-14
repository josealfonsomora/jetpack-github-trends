package com.josealfonsomora.jetpackgithubtrends.data.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.dao.GithubLicenseDao
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.dao.GithubOwnerDao
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.dao.GithubRepoDao
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.GithubLicenseEntity
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.GithubOwnerEntity
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.GithubRepoEntity

@Database(
    entities = [GithubRepoEntity::class, GithubOwnerEntity::class, GithubLicenseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubReposDb : RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
    abstract fun githubOwnerDao(): GithubOwnerDao
    abstract fun githubLicenseDao(): GithubLicenseDao
}
