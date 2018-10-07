package com.github.repo.ui

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, url: String?) {

    Glide.with(imageView.context)
            .load(url)
            .into(imageView)

}
