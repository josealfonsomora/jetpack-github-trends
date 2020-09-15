package com.josealfonsomora.jetpackgithubtrends.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.josealfonsomora.jetpackgithubtrends.R

@BindingAdapter("githubAvatarUrl")
fun setGithubAvatar(view: ImageView?, url: LiveData<String>?) {
    view?.let {
        url?.let {
            Glide
                .with(view)
                .load(url.value)
                .centerCrop()
                .placeholder(R.drawable.github_placeholder)
                .apply(RequestOptions().circleCrop())
                .into(view)
        }
    }
}
