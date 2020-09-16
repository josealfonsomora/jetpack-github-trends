package com.josealfonsomora.jetpackgithubtrends.ui.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.jetpackgithubtrends.commons.toReadableK
import com.josealfonsomora.jetpackgithubtrends.databinding.GithubReposListAdapterItemBinding
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo

class GithubReposListAdapter(
    private var items: List<GithubRepo> = emptyList(),
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<GithubReposListAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GithubReposListAdapterItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[holder.bindingAdapterPosition]
        holder.bind(item)
        holder.itemView.setOnClickListener { item.id?.let { onClickListener(it) } }
    }

    fun updateList(list: List<GithubRepo>) {
        items = list
        notifyDataSetChanged()
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

class GithubReposListAdapterItemViewModel(
    val name: String?,
    val stars: String?,
    val watchers: String?,
    val forks: String?,
    val license: String?
) {
    val licenseVisibility = if (license.isNullOrEmpty()) View.GONE else View.VISIBLE
}
