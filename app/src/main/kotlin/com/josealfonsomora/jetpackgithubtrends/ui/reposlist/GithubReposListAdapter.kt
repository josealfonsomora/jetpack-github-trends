package com.josealfonsomora.jetpackgithubtrends.ui.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.jetpackgithubtrends.R
import com.josealfonsomora.jetpackgithubtrends.commons.toReadableK
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.ui.custom.GithubCardViewModel
import com.josealfonsomora.jetpackgithubtrends.ui.custom.GithubItemView

class GithubReposListAdapter(
    private var items: List<GithubRepo> = emptyList(),
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<GithubReposListAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.github_repps_custom_view_list_adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[holder.bindingAdapterPosition]
        holder.githubView.model = GithubCardViewModel(
            item.fullName,
            item.stargazersCount.toReadableK(),
            item.watchers.toReadableK(),
            item.forks.toReadableK(),
            item.license?.name
        )
        holder.itemView.setOnClickListener { item.id?.let { onClickListener(it) } }
    }

    fun updateList(list: List<GithubRepo>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val githubView = view.findViewById<GithubItemView>(R.id.item)
    }
}
