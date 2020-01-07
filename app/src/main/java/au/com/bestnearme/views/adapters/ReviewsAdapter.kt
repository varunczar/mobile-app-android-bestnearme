package au.com.bestnearme.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.placedetails.ReviewsItem
import au.com.bestnearme.views.placedetails.viewholders.ReviewViewHolder

class ReviewsAdapter(val mReviews: List<ReviewsItem>, val context: Context) : RecyclerView.Adapter<ReviewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

        return ReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_reviews_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mReviews.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(mReviews.get(position))
    }

}