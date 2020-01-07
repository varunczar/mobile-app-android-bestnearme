package au.com.bestnearme.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.views.utils.loadImageFromCode
import kotlinx.android.synthetic.main.layout_categories_row.view.*

class CategoriesAdapter(var mCategories : ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    init {
        setHasStableIds(true)
    }

    var mOnCategoryClickListener : OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_categories_row, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = mCategories[position]
        category.apply {
            holder.mTextViewCategory.text = displayName
            holder.mImageViewCategoriesIcon.loadImageFromCode(code)
            holder.mItemView.setOnClickListener { mOnCategoryClickListener?.onCategoryClicked(this) }

            //holder.mImageViewCategoriesIcon.setImageDrawable(R.drawable.coffee)
        }

    }

    override fun getItemCount(): Int {
        return mCategories.size
    }

    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mItemView: View
        val mImageViewCategoriesIcon: ImageView
        val mTextViewCategory: TextView

        init {
            mItemView = itemView
            mImageViewCategoriesIcon = itemView.image_view_category_icon
            mTextViewCategory = itemView.textview_category
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun updateCategories(newCategories : List<Category>) {
        mCategories.clear()
        mCategories.addAll(newCategories)
        notifyDataSetChanged()
    }

    interface OnCategoryClickListener {
        fun onCategoryClicked(category: Category)
    }
}