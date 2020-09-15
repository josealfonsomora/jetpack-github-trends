package com.josealfonsomora.jetpackgithubtrends.domain.usecase

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josealfonsomora.jetpackgithubtrends.CoroutinesTestRule
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.domain.repository.GithubReposRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

@ExperimentalCoroutinesApi
class GetGithubRepoUseCaseTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val ctr = CoroutinesTestRule()

    private val repository: GithubReposRepository = mockk(relaxed = true) {
        coEvery { getGithubRepos() } returns GithubReposRepository.Result.Success(emptyList<GithubRepo>())
    }

    private val underTest = GetGithubRepoUseCase(repository)

    @Test
    fun `returns github repository from repository`() = ctr.dispatcher.runBlockingTest {
        val repo = GithubRepo()
        val repoId = 1
        coEvery { repository.getGithubRepo(repoId) } returns GithubReposRepository.Result.Success(repo)

        val result = underTest.execute(repoId)
        assertTrue(result is GetGithubRepoUseCase.Result.Success)
        val data = (result as GetGithubRepoUseCase.Result.Success).data
        assertEquals(repo, data)
    }

    @Test
    fun `returns error from network`() = ctr.dispatcher.runBlockingTest {
        val error = NetworkErrorException("any error")
        val repoId = 1
        coEvery { repository.getGithubRepo(repoId) } returns GithubReposRepository.Result.Error(error)

        val result = underTest.execute(repoId)
        assertTrue(result is GetGithubRepoUseCase.Result.Error)
        val data = (result as GetGithubRepoUseCase.Result.Error).throwable
        assertEquals(error, data)
    }
}