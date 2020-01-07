package au.com.bestnearme.views.adapters

import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import au.com.bestnearme.R
import au.com.bestnearme.data.IntroFragmentComponent


class IntroPagerAdapter (private val context: Context,
                         private var introFragmentComponents: List<IntroFragmentComponent>) : PagerAdapter() {

    override fun instantiateItem(@NonNull collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.fragment_intro, collection, false) as ViewGroup
        val imageViewIcon = layout.findViewById<View>(R.id.image_view_icon) as AppCompatImageView
        val textViewTitle = layout.findViewById<TextView>(R.id.text_view_screen_title) as TextView
        val introFragmentComponent = introFragmentComponents[position]
        introFragmentComponent.apply {
            imageViewIcon.setImageResource(imageIcon)
            textViewTitle.text = context.resources.getString(screenText)
        }
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull view: Any) {
        container.removeView(view as View)
    }

    override fun getCount(): Int {
        return this.introFragmentComponents.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}