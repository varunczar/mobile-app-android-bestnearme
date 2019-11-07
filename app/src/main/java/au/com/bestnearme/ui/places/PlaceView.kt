package au.com.bestnearme.ui.places

import au.com.bestnearme.data.PostData
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.ui.IView

interface PlaceView: IView {

    fun showPlaces(places: List<Place>)
}