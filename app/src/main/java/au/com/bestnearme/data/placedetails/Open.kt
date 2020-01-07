package au.com.bestnearme.data.placedetails

import com.google.gson.annotations.SerializedName

data class Open(@SerializedName("time")
                val time: String = "",
                @SerializedName("day")
                val day: Int = 0)