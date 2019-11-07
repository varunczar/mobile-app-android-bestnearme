package au.com.bestnearme.data.categories

import com.google.gson.annotations.SerializedName

data class Category(@SerializedName("code")
                    val code: String = "",
                    @SerializedName("displayName")
                    val displayName: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("heroImageUrl")
                    val heroImageUrl: String = "")