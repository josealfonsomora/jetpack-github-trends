package com.josealfonsomora.jetpackgithubtrends.data.persistence.database

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.josealfonsomora.jetpackgithubtrends.data.model.GithubRepoDataModel
import com.josealfonsomora.jetpackgithubtrends.data.model.toDatabaseEntity
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.toDomainModel
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import kotlinx.coroutines.flow.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.ChronoUnit
import timber.log.Timber
import javax.inject.Inject

const val CACHE_PERSISTENCE_TIME_MINUTES = 1

class AppDatabase @Inject constructor(
    private val database: GithubReposDb,
    private val cacheDataStore: DataStore<Preferences>
) {
    private val UPDATE_TIME = preferencesKey<Long>("update_time")

    suspend fun getGithubRepos(): List<GithubRepo> = try {
        val shouldUseCache = cacheDataStore.data
            .map { cache -> cache[UPDATE_TIME] }
            .catch { Timber.e(it) }
            .map { LocalDateTime.ofInstant(Instant.ofEpochMilli(it ?: 0), ZoneOffset.UTC) }
            .map { it.until(LocalDateTime.now(), ChronoUnit.MINUTES) }
            .map { CACHE_PERSISTENCE_TIME_MINUTES > it }
            .firstOrNull()

        if (shouldUseCache == true) {
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
        } else {
            emptyList()
        }
    } catch (e: Throwable) {
        emptyList()
    }

    suspend fun saveGithubRepos(list: List<GithubRepoDataModel>) {
        cacheDataStore.edit { cache ->
            cache[UPDATE_TIME] =
                LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
        }
        database.githubRepoDao().insertAll(list.map { it.toDatabaseEntity() })
        database.githubOwnerDao()
            .insertAll(list.filter { it.owner != null }.map { it.owner!!.toDatabaseEntity() })
        database.githubLicenseDao()
            .insertAll(list.filter { it.license != null }.map { it.license!!.toDatabaseEntity() })
    }

}
