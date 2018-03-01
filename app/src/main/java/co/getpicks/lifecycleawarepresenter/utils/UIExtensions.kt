package co.getpicks.lifecycleawarepresenter.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.go() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ImageView.load(url: String) {
    Glide.with(context).load(url).into(this)
}

