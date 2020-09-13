package com.josealfonsomora.jetpackgithubtrends.ui.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.jetpackgithubtrends.R
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepository

class GithubReposListAdapter(
    var items: List<GithubRepository> = emptyList(),
    val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<GithubReposListAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.github_repos_list_adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[holder.adapterPosition]
        holder.bind(item)
        holder.layout.setOnClickListener { onClickListener(item.id) }
    }

    fun updateList(list: List<GithubRepository>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val layout: ConstraintLayout = view.findViewById(R.id.layout)

        fun bind(item: GithubRepository) {
            name.text = item.name
        }
    }
}

class GithubReposListAdapterItem(
    val id: Int,
    val name: String
)