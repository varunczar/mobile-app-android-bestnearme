package au.com.bestnearme.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.views.utils.loadImage
import au.com.bestnearme.views.utils.roundToDouble
import au.com.bestnearme.views.utils.setOpenCloseHours
import kotlinx.android.synthetic.main.layout_place_row.view.*

class PlacesAdapter(var mPlaces : ArrayList<Place>) : RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    init {
        setHasStableIds(true)
    }

    var mOnPlaceClickListener : OnPlaceClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_place_row, parent, false)
        return PlacesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val place = mPlaces[position]
        place.apply {
            holder.mTextViewPlaceName.text = name
            holder.mTextViewPlaceAddress.text = vicinity
            holder.mTextViewRating.text = trueRating.roundToDouble()
            holder.mImageViewPlace.layout(0,0,0,0)
            holder.mImageViewPlace.setImageDrawable(null)
            holder.mTextViewOpenClose.setOpenCloseHours(openingHours, holder.mTextViewOpenClose.context)
            photos?.let {
                if(it.size>0 && it.get(0).photoReference != null)
                {
                    holder.mImageViewPlace.visibility=View.VISIBLE
                    holder.mImageViewPlace.loadImage(it.get(0).photoReference)
                }
                else {
                    holder.mImageViewPlace.visibility=View.GONE
                }

            }
            holder.mItemView.setOnClickListener {
                mOnPlaceClickListener?.onPlaceClicked(placeId, trueRating)
            }

        }

    }

    override fun getItemCount(): Int {
        return mPlaces.size
    }

    class PlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mItemView: View
        val mImageViewPlace: ImageView
        val mTextViewPlaceName: TextView
        val mTextViewPlaceAddress: TextView
        val mTextViewOpenClose: TextView
        val mTextViewRating: TextView

        init {
            mItemView = itemView
            mImageViewPlace = itemView.image_view_place
            mTextViewPlaceName = itemView.text_view_place_name
            mTextViewPlaceAddress = itemView.text_view_place_address
            mTextViewRating = itemView.text_view_rating
            mTextViewOpenClose = itemView.text_view_open_close
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun updatePlaces(newPlaces : List<Place>) {
        mPlaces.clear()
        mPlaces.addAll(newPlaces)
        notifyDataSetChanged()
    }

    interface OnPlaceClickListener {
        fun onPlaceClicked(placeId: String, trueRating : Double)
    }
}