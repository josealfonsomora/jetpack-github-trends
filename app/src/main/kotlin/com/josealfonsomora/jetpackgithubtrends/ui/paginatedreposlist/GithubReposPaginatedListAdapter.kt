package com.josealfonsomora.jetpackgithubtrends.ui.paginatedreposlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.jetpackgithubtrends.commons.toReadableK
import com.josealfonsomora.jetpackgithubtrends.databinding.GithubReposListAdapterItemBinding
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo

class GithubReposPaginatedListAdapter(
    private val onClickListener: (Int) -> Unit
) : PagingDataAdapter<GithubRepo, GithubReposPaginatedListAdapter.ViewHolder>
    (diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GithubReposListAdapterItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { repo ->
            holder.bind(repo)
            holder.itemView.setOnClickListener { repo.id?.let { onClickListener(it) } }
        }
    }

    class ViewHolder(val binding: GithubReposListAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubRepo) {
            binding.model = GithubReposListAdapterItemViewModel(
                item.fullName,
                item.stargazersCount.toReadableK(),
                item.watchers.toReadableK(),
                item.forks.toReadableK(),
                item.license?.name

            )
        }
    }
}

val diffCallback = object : DiffUtil.ItemCallback<GithubRepo>() {
    override fun areItemsTheSame(
        oldItem: GithubRepo,
        newItem: GithubRepo
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: GithubRepo,
        newItem: GithubRepo
    ): Boolean = oldItem == newItem
}
