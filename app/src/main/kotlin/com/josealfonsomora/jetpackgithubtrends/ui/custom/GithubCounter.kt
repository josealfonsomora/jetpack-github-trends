package com.josealfonsomora.jetpackgithubtrends.ui.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleRegistryOwner
import androidx.lifecycle.LiveData
import com.josealfonsomora.jetpackgithubtrends.R
import kotlin.properties.Delegates

class GithubCounter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var titleTextView: TextView
    private lateinit var counterTextView: TextView
    private lateinit var iconImageView: ImageView

    var counter: String by Delegates.observable("") { _, _, text ->
        counterTextView.text = text
        invalidate()
    }

    var title: String by Delegates.observable("") { _, _, text ->
        titleTextView.text = text
        invalidate()
    }

    var icon: Drawable? by Delegates.observable(null) { _, _, drawable ->
        if (drawable != null) {
            iconImageView.setImageDrawable(drawable)
        } else {
            iconImageView.visibility = View.GONE
        }
        invalidate()
    }

    init {
        val view = View.inflate(context, R.layout.layout_github_counter, this)
        titleTextView = view.findViewById(R.id.title)
        counterTextView = view.findViewById(R.id.counter)
        iconImageView = view.findViewById(R.id.icon)

        context.theme.obtainStyledAttributes(attrs, R.styleable.GithubCounter, 0, 0).apply {
            try {
                title = getString(R.styleable.GithubCounter_git_hub_counter_title)!!
                icon = ResourcesCompat.getDrawable(
                    resources,
                    getResourceId(R.styleable.GithubCounter_git_hub_counter_icon, 0),
                    context.theme
                )
            } finally {
                recycle()
            }
        }
    }
}