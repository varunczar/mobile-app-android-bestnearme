package au.com.bestnearme.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.MyApplication
import au.com.bestnearme.R
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.ui.utils.glideRequestOptions
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_categories_row.view.*
import kotlinx.android.synthetic.main.layout_place_row.view.*

class CategoriesAdapter(val mContext: Context, val mCategories : List<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_categories_row, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = mCategories[position]
        category.apply {
            holder.mTextViewCategory.text = displayName
            holder.mImageViewCategoriesIcon.setImageResource(mContext.getResources().
                getIdentifier(code, "drawable", mContext.getPackageName()))

            //holder.mImageViewCategoriesIcon.setImageDrawable(R.drawable.coffee)
        }

    }

    override fun getItemCount(): Int {
        return mCategories?.size ?: 0
    }

    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mImageViewCategoriesIcon: ImageView
        val mTextViewCategory: TextView

        init {

            mImageViewCategoriesIcon = itemView.image_view_category_icon
            mTextViewCategory = itemView.textview_category
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}