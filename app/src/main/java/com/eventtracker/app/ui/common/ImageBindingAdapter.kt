package com.eventtracker.app.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageBindingAdapter {
    @BindingAdapter("android:src")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .fit()
            .into(view)
    }
}