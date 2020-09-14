package com.josealfonsomora.jetpackgithubtrends.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josealfonsomora.jetpackgithubtrends.CoroutinesTestRule
import com.josealfonsomora.jetpackgithubtrends.data.model.GithubRepoDataModel
import com.josealfonsomora.jetpackgithubtrends.data.network.GetGithubRepositoriesResponse
import com.josealfonsomora.jetpackgithubtrends.data.network.GithubApi
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.AppDatabase
import com.josealfonsomora.jetpackgithubtrends.domain.exceptions.EmptyContentException
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class GithubReposRepositoryImpTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val ctr = CoroutinesTestRule()

    private val api: GithubApi = mockk(relaxed = true)

    private val database: AppDatabase = mockk(relaxUnitFun = true) {
        coEvery { getAllGithubRepos() } returns emptyList()
    }

    private val underTest = GithubReposRepositoryImp(api, database)

    @Test
    fun `return repos from Github api`() = ctr.dispatcher.runBlockingTest {

        val repoDataModel: GithubRepoDataModel =
            mockk(relaxed = true, relaxUnitFun = true)
        val list = listOf(
            repoDataModel,
        )
        val response = GetGithubRepositoriesResponse(
            count = 1,
            incompleteResults = false,
            items = list
        )
        coEvery { api.getRepositories() } returns Response.success(
            response
        )
        val result = underTest.getGithubRepos()

        assertTrue(result is GithubReposRepository.Result.Success<*>)
    }

    @Test
    fun `return error if error from Github api`() = ctr.dispatcher.runBlockingTest {
        val response = Response.error<GetGithubRepositoriesResponse>(
            404,
            "error message".toResponseBody("text/plain".toMediaTypeOrNull())
        )

        coEvery { api.getRepositories() } returns response

        val result = underTest.getGithubRepos()

        assertTrue(result is GithubReposRepository.Result.Error<*>)
    }

    @Test
    fun `return EmptyContentException when content is null`() = ctr.dispatcher.runBlockingTest {
        val response = GetGithubRepositoriesResponse(
            count = 1,
            incompleteResults = false,
            items = null
        )
        coEvery { api.getRepositories() } returns Response.success(
            response
        )
        val result = underTest.getGithubRepos()

        assertTrue(result is GithubReposRepository.Result.Error<*>)

        val error = (result as GithubReposRepository.Result.Error<*>).throwable

        assertTrue(error is EmptyContentException)
    }

    @Test
    fun `returns exception when api throws exception`() = ctr.dispatcher.runBlockingTest {
        val illegalArgumentException = IllegalArgumentException("any error")

        coEvery { api.getRepositories() } throws illegalArgumentException
        val result = underTest.getGithubRepos()

        assertTrue(result is GithubReposRepository.Result.Error<*>)

        val error = (result as GithubReposRepository.Result.Error<*>).throwable

        assertEquals(illegalArgumentException, error)
    }

    @Test
    fun `returns saved data from database when available`() = ctr.dispatcher.runBlockingTest {
        val repoDataModel: GithubRepoDataModel = mockk(relaxed = true, relaxUnitFun = true)
        val list = listOf(repoDataModel)
        val response = GetGithubRepositoriesResponse(
            count = 1,
            incompleteResults = false,
            items = list
        )
        coEvery { api.getRepositories() } returns Response.success(response)
        val databaseRepos = listOf(mockk<GithubRepo>())
        coEvery { database.getAllGithubRepos() } returns databaseRepos
        val result = underTest.getGithubRepos()

        assertTrue(result is GithubReposRepository.Result.Success<*>)
        assertEquals(databaseRepos, (result as GithubReposRepository.Result.Success<*>).data)
    }
}
