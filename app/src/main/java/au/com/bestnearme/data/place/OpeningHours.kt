package au.com.bestnearme.data.place

import com.google.gson.annotations.SerializedName

data class OpeningHours(@SerializedName("open_now")
                        val openNow: Boolean = false)