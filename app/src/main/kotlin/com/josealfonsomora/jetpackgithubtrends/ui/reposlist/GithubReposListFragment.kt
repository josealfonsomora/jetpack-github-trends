package com.josealfonsomora.jetpackgithubtrends.ui.reposlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.josealfonsomora.jetpackgithubtrends.R
import com.josealfonsomora.jetpackgithubtrends.databinding.GithubReposListFragmentBinding
import com.josealfonsomora.jetpackgithubtrends.ui.repodetails.GithubRepoDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubReposListFragment : Fragment(R.layout.github_repos_list_fragment) {
    private val viewModel: GitHubReposListViewModel by viewModels()

    private val adapter by lazy {
        GithubReposListAdapter {
            val action =
                GithubReposListFragmentDirections.actionGithubReposListDestToGithubRepoDetailDest(it)
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = GithubReposListFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.model = viewModel

        binding.reposList.adapter = adapter

        viewModel.githubRepos.observe(viewLifecycleOwner, { list ->
            adapter.updateList(list)
        })

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
        }

        viewModel.stateEvent.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { state ->
                when (state) {
                    GitHubReposListViewModel.State.ErrorLoadingReposEvent -> showErrorLoadingReposMessage()
                }
            }
        })

        viewModel.loadData()
    }

    private fun showErrorLoadingReposMessage() {
        Toast.makeText(requireContext(), R.string.error_loading_repos, Toast.LENGTH_SHORT).show()
    }
}
