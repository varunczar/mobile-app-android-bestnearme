package au.com.bestnearme.data.place

import com.google.gson.annotations.SerializedName

data class Place(@SerializedName("types")
                 val types: List<String>?,
                 @SerializedName("icon")
                 val icon: String = "",
                 @SerializedName("rating")
                 val rating: Double = 0.0,
                 @SerializedName("photos")
                 val photos: List<PhotosItem>?,
                 @SerializedName("reference")
                 val reference: String = "",
                 @SerializedName("user_ratings_total")
                 val userRatingsTotal: Int = 0,
                 @SerializedName("price_level")
                 val priceLevel: Int = 0,
                 @SerializedName("true_rating")
                 val trueRating: Double = 0.0,
                 @SerializedName("scope")
                 val scope: String = "",
                 @SerializedName("name")
                 val name: String = "",
                 @SerializedName("opening_hours")
                 val openingHours: OpeningHours,
                 @SerializedName("geometry")
                 val geometry: Geometry,
                 @SerializedName("vicinity")
                 val vicinity: String = "",
                 @SerializedName("id")
                 val id: String = "",
                 @SerializedName("plus_code")
                 val plusCode: PlusCode,
                 @SerializedName("place_id")
                 val placeId: String = "")