package com.josealfonsomora.jetpackgithubtrends.ui.reposlist

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.josealfonsomora.jetpackgithubtrends.CoroutinesTestRule
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.domain.usecase.GetGithubReposUseCase
import com.josealfonsomora.jetpackgithubtrends.ui.Event
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@ExperimentalCoroutinesApi
class GitHubReposListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val ctr = CoroutinesTestRule()

    private val userCase: GetGithubReposUseCase = mockk(relaxed = true) {
        coEvery { execute() } returns GetGithubReposUseCase.Result.Success(emptyList())
    }

    lateinit var underTest: GitHubReposListViewModel

    @Before
    fun setUp() {
        underTest = GitHubReposListViewModel(userCase, ctr.dispatcher)
    }

    @Test
    fun `shows loading spinner until data is loaded`() = ctr.dispatcher.runBlockingTest {
        val observer = spyk<Observer<Int>>()
        underTest.loadingVisibility.observeForever(observer)
        underTest.loadData()
        verifySequence {
            observer.onChanged(View.GONE)
            observer.onChanged(View.VISIBLE)
            observer.onChanged(View.GONE)
        }
    }

    @Test
    fun `shows loading spinner until error`() = ctr.dispatcher.runBlockingTest {
        coEvery { userCase.execute() } returns GetGithubReposUseCase.Result.Error(Throwable("Any error"))
        val observer = spyk<Observer<Int>>()
        underTest.loadingVisibility.observeForever(observer)
        underTest.loadData()
        verifySequence {
            observer.onChanged(View.GONE)
            observer.onChanged(View.VISIBLE)
            observer.onChanged(View.GONE)
        }
    }

    @Test
    fun `loads data using use case`() {
        val list = listOf(
            GithubRepo(name = "repo 1"),
            GithubRepo(name = "repo 2")
        )
        coEvery { userCase.execute() } returns GetGithubReposUseCase.Result.Success(list)

        val observer = spyk<Observer<List<GithubRepo>>>()
        underTest.githubRepos.observeForever(observer)

        underTest.loadData()

        verify { observer.onChanged(list) }
    }

    @Test
    fun `emits an state event when error from use case`() {
        val error = GetGithubReposUseCase.Result.Error(Throwable("Any error"))
        coEvery { userCase.execute() } returns error

        val observer = spyk<Observer<Event<GitHubReposListViewModel.State>>>()
        underTest.stateEvent.observeForever(observer)

        underTest.loadData()

        val captor = CapturingSlot<Event<GitHubReposListViewModel.State>>()
        verify { observer.onChanged(capture(captor)) }
        val eventCaptured = captor.captured.getContentIfNotHandled()
        assertEquals(GitHubReposListViewModel.State.ErrorLoadingReposEvent, eventCaptured)
    }
}
