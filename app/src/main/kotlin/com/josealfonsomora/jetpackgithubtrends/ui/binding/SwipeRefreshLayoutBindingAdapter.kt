package com.josealfonsomora.jetpackgithubtrends.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("app:refreshing")
fun refreshing(view: SwipeRefreshLayout?, visibility: Int?) {
    view?.let {
        it.isRefreshing = visibility == View.VISIBLE
    }
}
