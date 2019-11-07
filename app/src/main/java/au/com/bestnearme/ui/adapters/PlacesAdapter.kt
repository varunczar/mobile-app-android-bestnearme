package au.com.bestnearme.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.ui.utils.glideRequestOptions
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_place_row.view.*

class PlacesAdapter(val mPlaces : List<Place>) : RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    init {
        setHasStableIds(true)
    }

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
            holder.mImageViewPlace.layout(0,0,0,0)
            if(photos?.get(0)?.photoReference != null)
            {
                Glide.with(holder.mImageViewPlace).load(photos?.get(0)?.photoReference).
                    apply(glideRequestOptions).into(holder.mImageViewPlace).clearOnDetach()
            }
            else {
                holder.mImageViewPlace.setImageDrawable(null)
            }


        }

    }

    override fun getItemCount(): Int {
        return mPlaces?.size ?: 0
    }

    class PlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mImageViewPlace: ImageView
        val mTextViewPlaceName: TextView
        val mTextViewPlaceAddress: TextView

        init {

            mImageViewPlace = itemView.image_view_place
            mTextViewPlaceName = itemView.text_view_place_name
            mTextViewPlaceAddress = itemView.text_view_place_address
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}