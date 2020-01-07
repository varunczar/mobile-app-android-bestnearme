package au.com.bestnearme.views.placedetails.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.data.placedetails.ReviewsItem
import kotlinx.android.synthetic.main.layout_reviews_item.view.*

class ReviewViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    val mTextViewReview : TextView = itemView.text_view_review
    val mTextViewAuthor : TextView = itemView.text_view_author
    val mTextViewRating : TextView = itemView.text_view_rating
    val mTextViewDesc : TextView = itemView.text_view_desc


    fun bind(review : ReviewsItem) {
        review.apply {
            mTextViewReview.text=text
            mTextViewAuthor.text=authorName
            mTextViewRating.text=rating.toString()
            mTextViewDesc.text=relativeTimeDescription
        }
    }
}