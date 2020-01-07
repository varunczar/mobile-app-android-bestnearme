package au.com.bestnearme.views.utils

import android.content.Context
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import au.com.bestnearme.R
import au.com.bestnearme.data.placedetails.OpeningHours
import au.com.bestnearme.data.placedetails.PlaceDetails
import au.com.bestnearme.views.placedetails.PlaceDetailsCellType

fun Double.roundToDouble(): String {
    return String.format("%.2f", this).toDouble().toString()
}

fun TextView.setOpenCloseHours(openingHours : OpeningHours?, context : Context, colourise : Boolean = false) {
    openingHours?.let {
        if(it.openNow) {
            this.text=resources.getString(R.string.label_open)
            if(colourise)
            this.setTextColor(
                ContextCompat.getColor(context,
                    R.color.colourGreen))
        }
        else {
            this.text=resources.getString(R.string.label_closed)
            if(colourise)
            this.setTextColor(
                ContextCompat.getColor(context,
                    R.color.colourRed))
        }
    }  ?: run {
        this.text=resources.getString(R.string.label_unknown)
        this.setTextColor(
            ContextCompat.getColor(context,
                R.color.colorGrey))
    }
}

fun PlaceDetails.getPlaceDetailsElements() : ArrayList<PlaceDetailsCellType>{
    val placeDetailsTypes : ArrayList<PlaceDetailsCellType> = arrayListOf()

    this.apply {
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(vicinity)) {
            placeDetailsTypes.add(PlaceDetailsCellType.HEADER)
        }
        geometry?.location?.let {
            placeDetailsTypes.add(PlaceDetailsCellType.MAP)
        }
        photos?.let {
            if(it.isNotEmpty()) placeDetailsTypes.add(PlaceDetailsCellType.PHOTOS)
        }
        reviews?.let {
            if(it.isNotEmpty()) placeDetailsTypes.add(PlaceDetailsCellType.REVIEWS)
        }
    }

    return placeDetailsTypes
}

fun showError(errorMessage : String, context: Context) {
    AlertDialog.Builder(context)
        .setTitle(context.getResources().getString(R.string.error))
        .setMessage(errorMessage)
        .setPositiveButton(context.getResources().getString(R.string.ok),null)
        .show()
}