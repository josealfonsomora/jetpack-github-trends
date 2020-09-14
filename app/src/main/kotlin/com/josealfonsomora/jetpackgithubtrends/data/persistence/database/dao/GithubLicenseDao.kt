package com.josealfonsomora.jetpackgithubtrends.data.persistence.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.GithubLicenseEntity

@Dao
interface GithubLicenseDao {
    @Query("SELECT * FROM license")
    suspend fun getAll(): List<GithubLicenseEntity>

    @Query("SELECT * FROM license WHERE `key` = :key")
    suspend fun getGithubRepository(key: String): GithubLicenseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<GithubLicenseEntity>)
}
