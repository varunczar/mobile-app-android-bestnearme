package au.com.bestnearme.data.placedetails

import com.google.gson.annotations.SerializedName

data class PeriodsItem(@SerializedName("close")
                       val close: Close,
                       @SerializedName("open")
                       val open: Open)