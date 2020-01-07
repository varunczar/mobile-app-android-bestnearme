package au.com.bestnearme.views.placedetails.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.data.placedetails.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.layout_map_row.view.*

class MapViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView), OnMapReadyCallback {

    val mMap : MapView? = itemView.map
    var mLocation : Location? = null
    var mName : String  = ""


    fun bind(location: Location?, name : String) {
        mLocation = location
        mName = name
        initializeMapView()

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mLocation?.let {
            val location = LatLng(it.lat, it.lng)
            googleMap?.let {
                it.addMarker(
                    MarkerOptions()
                        .position(location)
                        .title(mName))
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            }
        }

    }

    fun initializeMapView() {
        mMap?.let {
            it.onCreate(null)
            it.onResume()
            it.getMapAsync(this)
        }
    }
}