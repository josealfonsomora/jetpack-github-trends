package com.josealfonsomora.jetpackgithubtrends.ui.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.josealfonsomora.jetpackgithubtrends.ui.custom.GithubCounter

@BindingAdapter("git_hub_counter")
fun setCounter(view: GithubCounter?, counter: LiveData<String>?) {
    view?.let {
        counter?.value?.let { value -> it.counter = (value) }
    }
}
