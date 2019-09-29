package com.pritam.githubmvvm.view.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.pritam.githubmvvm.R
import com.squareup.picasso.Picasso

class CustomBindingAdapter {
    companion object {
        // Custom binding adapter for visibility
        @JvmStatic
        @BindingAdapter("visibleGone")
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        /// static binding adapter to load image using picasso
        @BindingAdapter("android:imageHref")
        @JvmStatic
        fun loadImage(factImageView: ImageView, imageHref: String?) {
            if (imageHref != "") {
                Picasso.get()
                        .load(imageHref)
                        .placeholder(R.drawable.no_image_placeholder)
                        .into(factImageView)
            }
        }
    }
}