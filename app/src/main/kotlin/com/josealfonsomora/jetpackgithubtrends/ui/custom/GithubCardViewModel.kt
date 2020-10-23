package com.josealfonsomora.jetpackgithubtrends.ui.custom

import android.view.View

class GithubCardViewModel(
    val name: String? = null,
    val stars: String? = null,
    val watchers: String? = null,
    val forks: String? = null,
    val license: String? = null
) {
    val licenseVisibility = if (license.isNullOrEmpty()) View.GONE else View.VISIBLE
}