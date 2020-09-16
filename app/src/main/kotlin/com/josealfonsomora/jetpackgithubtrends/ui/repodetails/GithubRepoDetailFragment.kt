package com.josealfonsomora.jetpackgithubtrends.ui.repodetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.josealfonsomora.jetpackgithubtrends.R
import com.josealfonsomora.jetpackgithubtrends.databinding.GithubRepoDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubRepoDetailFragment : Fragment(R.layout.github_repo_detail_fragment) {
    private val viewModel: GitHubRepoDetailViewModel by viewModels()

    private val args: GithubRepoDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = GithubRepoDetailFragmentBinding.bind(view)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.lifecycleOwner = this
        binding.model = viewModel

        viewModel.title.observe(viewLifecycleOwner, {
            toolbar.title = it
        })
        viewModel.loadData(args.repoId)
    }
}
