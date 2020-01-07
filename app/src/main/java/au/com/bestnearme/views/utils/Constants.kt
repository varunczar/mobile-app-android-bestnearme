package au.com.bestnearme.views.utils

import au.com.bestnearme.R
import au.com.bestnearme.data.IntroFragmentComponent

object Constants {


    val KEY_IS_INTRO_SHOWN = "is_intro_shown"

    val KEY_CATEGORY = "category"
    val KEY_PLACE_ID = "place_id"
    val KEY_TRUE_RATING = "true_rating"

    val REQUEST_INTRO = 100
    val REQUEST_LOCATION = 101
    val REQUEST_GPS = 102

    val FILE_NAME_CACHE = "cache"
    val KEY_LAST_KNOWN_LAT = "last_lat"
    val KEY_LAST_KNOWN_LONG = "last_long"

    val INTRO_SCREEN_OBJECTS = ArrayList<IntroFragmentComponent>()

    init {
        INTRO_SCREEN_OBJECTS.add(IntroFragmentComponent(R.drawable.ic_award,R.string.message_01))
        INTRO_SCREEN_OBJECTS.add(IntroFragmentComponent(R.drawable.ic_bistro,R.string.message_02))
        INTRO_SCREEN_OBJECTS.add(IntroFragmentComponent(R.drawable.ic_global,R.string.message_03))
    }


}