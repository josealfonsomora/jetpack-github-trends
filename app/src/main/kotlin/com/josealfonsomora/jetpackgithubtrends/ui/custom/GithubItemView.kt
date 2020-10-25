package com.josealfonsomora.jetpackgithubtrends.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.josealfonsomora.jetpackgithubtrends.R
import com.josealfonsomora.jetpackgithubtrends.databinding.CardViewGitHubRepoBinding

class GithubItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
      defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    val binding: CardViewGitHubRepoBinding

    var model = GithubCardViewModel()
        set(value) {
            field = value
            binding.model = value
        }

    init {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.card_view_git_hub_repo,
            this,
            true
        )
        elevation = context.resources.getDimension(R.dimen.card_elevation)
    }
}
