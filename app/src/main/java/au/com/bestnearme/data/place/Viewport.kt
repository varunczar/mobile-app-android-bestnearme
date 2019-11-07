package au.com.bestnearme.data.place

import com.google.gson.annotations.SerializedName

data class Viewport(@SerializedName("southwest")
                    val southwest: Southwest,
                    @SerializedName("northeast")
                    val northeast: Northeast)