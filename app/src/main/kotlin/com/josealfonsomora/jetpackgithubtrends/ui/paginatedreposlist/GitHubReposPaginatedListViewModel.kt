package com.josealfonsomora.jetpackgithubtrends.ui.paginatedreposlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.josealfonsomora.jetpackgithubtrends.data.repository.GithubPagingSource
import com.josealfonsomora.jetpackgithubtrends.domain.usecase.GetGithubReposPaginatedUseCase

class GitHubReposPaginatedListViewModel @ViewModelInject constructor(
    private val useCase: GetGithubReposPaginatedUseCase
) : ViewModel() {

    val githubReposPagingFlow = Pager(PagingConfig(pageSize = 20)) {
        GithubPagingSource(useCase)
    }.flow.cachedIn(viewModelScope)
}
