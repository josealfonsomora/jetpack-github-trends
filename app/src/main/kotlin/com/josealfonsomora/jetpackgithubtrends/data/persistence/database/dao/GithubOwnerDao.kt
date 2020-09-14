package com.josealfonsomora.jetpackgithubtrends.data.persistence.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.GithubOwnerEntity

@Dao
interface GithubOwnerDao {
    @Query("SELECT * FROM owner")
    suspend fun getAll(): List<GithubOwnerEntity>

    @Query("SELECT * FROM owner WHERE id = :id")
    suspend fun getGithubRepository(id: Int): GithubOwnerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<GithubOwnerEntity>)
}
