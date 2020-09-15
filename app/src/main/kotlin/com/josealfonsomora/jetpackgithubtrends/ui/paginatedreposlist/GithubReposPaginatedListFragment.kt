package com.josealfonsomora.jetpackgithubtrends.ui.paginatedreposlist

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.jetpackgithubtrends.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubReposPaginatedListFragment : Fragment(R.layout.github_repos_paginated_list_fragment) {
    private val viewModel: GitHubReposPaginatedListViewModel by viewModels()

    private val adapter by lazy {
        GithubReposPaginatedListAdapter {
            val action =
                GithubReposPaginatedListFragmentDirections.actionGithubReposPaginatedListDestToGithubRepoDetailDest(
                    it
                )
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.reposList).adapter = adapter
        val loadingProgress = view.findViewById<ProgressBar>(R.id.loadingProgress)
        adapter.addLoadStateListener {
            if (it.refresh == LoadState.Loading) {
                loadingProgress.visibility = View.VISIBLE
            } else {
                loadingProgress.visibility = View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.githubReposPagingFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}
