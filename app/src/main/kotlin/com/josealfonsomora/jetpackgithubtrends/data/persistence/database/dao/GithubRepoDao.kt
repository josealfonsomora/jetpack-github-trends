package com.josealfonsomora.jetpackgithubtrends.data.persistence.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.GithubRepoEntity

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM repo")
    suspend fun getAll(): List<GithubRepoEntity>

    @Query("SELECT * FROM repo WHERE id = :id")
    suspend fun getGithubRepository(id: Int): GithubRepoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<GithubRepoEntity>)
}
