package com.josealfonsomora.jetpackgithubtrends.ui.paginatedreposlist

import android.view.View

class GithubReposListAdapterItemViewModel(
    val name: String?,
    val stars: String?,
    val watchers: String?,
    val forks: String?,
    val license: String?
) {
    val licenseVisibility = if (license.isNullOrEmpty()) View.GONE else View.VISIBLE
}