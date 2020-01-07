package au.com.bestnearme.views.placedetails.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.data.placedetails.PlaceDetails
import au.com.bestnearme.views.utils.loadImage
import au.com.bestnearme.views.utils.roundToDouble
import au.com.bestnearme.views.utils.setOpenCloseHours
import kotlinx.android.synthetic.main.layout_header_row.view.*

class HeaderViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    val mImageHero : ImageView = itemView.image_hero
    val mTextViewPlaceName : TextView = itemView.text_view_place_name
    val mTextViewPlaceAddress : TextView = itemView.text_view_place_address
    val mTextViewOpenClose : TextView = itemView.text_view_open_close
    val mTextViewRating : TextView = itemView.text_view_rating
    val mTextViewHours : TextView = itemView.text_view_hours
    val mTextViewPhone : TextView = itemView.text_view_phone

    fun bind(placeDetails: PlaceDetails?, trueRating : Double) {

        placeDetails?.apply {
            mTextViewPlaceName.text = name
            mTextViewPlaceAddress.text = vicinity
            mTextViewOpenClose.setOpenCloseHours(openingHours, mTextViewOpenClose.context)
            mTextViewRating.text = trueRating.roundToDouble()
            mTextViewHours.text = openingHours?.weekdayText?.joinToString("\n\n")
            mTextViewPhone.text = internationalPhoneNumber
            mTextViewPhone.setOnClickListener {
                val intent =  Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+internationalPhoneNumber))
                startActivity(mTextViewPhone.context,intent,null)
            }

            photos?.let {
                if(it.isNotEmpty()) {
                    mImageHero.loadImage(it[0].photoReference)
                }
            }

        }
    }
}