package au.com.bestnearme.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.bestnearme.R
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.ui.adapters.PlacesAdapter
import au.com.bestnearme.ui.places.PlacePresenterImpl
import au.com.bestnearme.ui.places.PlaceView
import au.com.bestnearme.ui.utils.MarginItemDecoration
import kotlinx.android.synthetic.main.layout_places_fragment.*


class PlacesFragment : Fragment, PlaceView {
    internal var mCategoryCode: String = ""
    internal var mApplicationComponent: Application ? = null
    var placePresenterImpl: PlacePresenterImpl?=null


    constructor() {}

    fun getPresenter(): PlacePresenterImpl?{
        placePresenterImpl = PlacePresenterImpl(this, mApplicationComponent!!)
        return placePresenterImpl
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_places_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_places.apply {
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.cell_padding_double).toInt(),false)
            )
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            adapter = PlacesAdapter(arrayListOf())
        }

    }

    override fun showPlaces(places: List<Place>) {

        recycler_view_places.adapter = PlacesAdapter(places)
    }

    override fun showLoading() {
        progress_circular.visibility=View.VISIBLE
    }

    override fun hideLoading() {
        progress_circular.visibility=View.GONE
    }

    override fun loadError(e: Throwable) {

    }

    override fun loadError(msg: String) {

    }

    companion object {

        @JvmStatic
        fun newInstance(categoryCode : String, applicationComponent: Application) =
            PlacesFragment().apply {
                mCategoryCode = categoryCode
                mApplicationComponent = applicationComponent
            }
    }

    fun fetchData() {
        getPresenter()?.let {
            it.getPlaces(mCategoryCode)
        }
    }



}
