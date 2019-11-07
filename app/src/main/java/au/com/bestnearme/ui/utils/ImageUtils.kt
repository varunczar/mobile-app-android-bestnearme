package au.com.bestnearme.ui.utils

import au.com.bestnearme.R
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

val glideRequestOptions = RequestOptions()
    .centerCrop()
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .placeholder(R.mipmap.ic_launcher_round)
    .priority(Priority.IMMEDIATE)
    .error(R.mipmap.ic_launcher_round)