package au.com.bestnearme.views.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import au.com.bestnearme.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


fun getProgressDrawable(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        setColorSchemeColors(ContextCompat.getColor(context,R.color.colorAccent))
        start()
    }
}

fun ImageView.loadImage(uri : String?) {
    val options = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(getProgressDrawable(this.context))
        .priority(Priority.IMMEDIATE)
    Glide.with(this).setDefaultRequestOptions(options).load(uri).into(this)
}

fun ImageView.loadImageFromCode(code : String){
    this.setImageResource(this.context.resources.
        getIdentifier(code, "drawable", this.context.packageName))

}

