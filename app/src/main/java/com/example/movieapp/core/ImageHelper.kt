package com.example.movieapp.core

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object ImageHelper {

    fun loadImage(url: String?, target: ImageView) {
        Glide.with(target.context)
            .load(url)
            .transform(CenterCrop(), RoundedCorners(26))
            .into(target)
    }
}