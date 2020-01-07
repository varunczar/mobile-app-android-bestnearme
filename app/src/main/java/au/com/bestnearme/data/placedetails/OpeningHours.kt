package au.com.bestnearme.data.placedetails

import com.google.gson.annotations.SerializedName

data class OpeningHours(@SerializedName("open_now")
                        val openNow: Boolean = false,
                        @SerializedName("periods")
                        val periods: List<PeriodsItem>?,
                        @SerializedName("weekday_text")
                        val weekdayText: List<String>?)