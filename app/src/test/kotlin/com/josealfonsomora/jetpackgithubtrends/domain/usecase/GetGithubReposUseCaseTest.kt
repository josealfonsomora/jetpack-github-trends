package com.josealfonsomora.jetpackgithubtrends.domain.usecase

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josealfonsomora.jetpackgithubtrends.CoroutinesTestRule
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepository
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
class GetGithubReposUseCaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val ctr = CoroutinesTestRule()

    private val repository: GithubReposRepository = mockk(relaxed = true) {
        coEvery { getGithubRepos() } returns GithubReposRepository.Result.Success(emptyList<GithubRepository>())
    }

    private val underTest = GetGithubReposUseCase(repository)

    @Test
    fun `returns list of github repositories from network`() = ctr.dispatcher.runBlockingTest {
        val list = emptyList<GithubRepository>()
        coEvery { repository.getGithubRepos() } returns GithubReposRepository.Result.Success(list)

        val result = underTest.execute()
        assertTrue(result is GetGithubReposUseCase.Result.Success)
        val data = (result as GetGithubReposUseCase.Result.Success).data
        assertEquals(list, data)
    }

    @Test
    fun `returns error from network`() = ctr.dispatcher.runBlockingTest {
        val error = NetworkErrorException("any error")
        coEvery { repository.getGithubRepos() } returns GithubReposRepository.Result.Error(error)

        val result = underTest.execute()
        assertTrue(result is GetGithubReposUseCase.Result.Error)
        val data = (result as GetGithubReposUseCase.Result.Error).throwable
        assertEquals(error, data)
    }
}
