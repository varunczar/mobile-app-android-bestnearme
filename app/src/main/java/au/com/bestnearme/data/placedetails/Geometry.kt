package au.com.bestnearme.data.placedetails

import com.google.gson.annotations.SerializedName

data class Geometry(@SerializedName("viewport")
                    val viewport: Viewport,
                    @SerializedName("location")
                    val location: Location?)