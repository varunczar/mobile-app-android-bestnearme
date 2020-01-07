package au.com.bestnearme.views.utils

import android.view.View
import androidx.viewpager.widget.ViewPager

class FadePageTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.setAlpha(1 - Math.abs(position))
        if (position < 0) {
            view.setScrollX((view.getWidth()  * position).toInt())
        } else if (position > 0) {
            view.setScrollX(-(view.getWidth()  * -position).toInt())
        } else {
            view.setScrollX(0)
        }
    }
}