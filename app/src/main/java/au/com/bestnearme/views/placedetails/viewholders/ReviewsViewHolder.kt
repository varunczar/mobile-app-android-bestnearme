package au.com.bestnearme.views.placedetails.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.placedetails.ReviewsItem
import au.com.bestnearme.views.adapters.ReviewsAdapter
import au.com.bestnearme.views.utils.DividerItemDecoration
import kotlinx.android.synthetic.main.layout_reviews_row.view.*

class ReviewsViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    val mRecyclerViewReviews : RecyclerView? = itemView.recycler_view_reviews


    fun bind(reviews : List<ReviewsItem>) {
        mRecyclerViewReviews?.context?.let {
            mRecyclerViewReviews.apply {
                layoutManager = LinearLayoutManager(it)
                adapter = ReviewsAdapter(reviews,it)
                addItemDecoration(
                    DividerItemDecoration(ContextCompat.getDrawable(context, R.drawable.divider_grey)!!)
                )
            }
        }
    }
}