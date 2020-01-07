package au.com.bestnearme.data.placedetails

import com.google.gson.annotations.SerializedName

data class ReviewsItem(@SerializedName("author_name")
                       val authorName: String = "",
                       @SerializedName("profile_photo_url")
                       val profilePhotoUrl: String = "",
                       @SerializedName("author_url")
                       val authorUrl: String = "",
                       @SerializedName("rating")
                       val rating: Int = 0,
                       @SerializedName("language")
                       val language: String = "",
                       @SerializedName("text")
                       val text: String = "",
                       @SerializedName("time")
                       val time: Int = 0,
                       @SerializedName("relative_time_description")
                       val relativeTimeDescription: String = "")