package com.josealfonsomora.jetpackgithubtrends.ui.repodetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josealfonsomora.jetpackgithubtrends.CoroutinesTestRule
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.domain.usecase.GetGithubRepoUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.threeten.bp.ZoneId

@ExperimentalCoroutinesApi
class GitHubRepoDetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val ctr = CoroutinesTestRule()

    private val repoMock = mockk<GithubRepo>(relaxed = true)
    private val userCase: GetGithubRepoUseCase = mockk(relaxed = true) {
        coEvery { execute(any()) } returns GetGithubRepoUseCase.Result.Success(repoMock)
    }

    private lateinit var underTest: GitHubRepoDetailViewModel

    @Before
    fun setUp() {
        underTest = GitHubRepoDetailViewModel(userCase, ctr.dispatcher, ZoneId.of("UTC"))
    }

    @Test
    fun `updates model from cached data`() = ctr.dispatcher.runBlockingTest {
        val fullName = "full name"
        val createdDate = "2016-02-05T13:42:07Z"
        every { repoMock.fullName } returns fullName
        every { repoMock.createdAt } returns createdDate

        underTest.loadData(1)

        assertEquals(fullName, underTest.name.value)
    }
}