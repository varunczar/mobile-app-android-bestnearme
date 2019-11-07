package au.com.bestnearme.ui.places

import android.os.Bundle
import au.com.bestnearme.R
import au.com.bestnearme.ui.BaseActivity
import au.com.bestnearme.ui.fragments.PlacesFragment
import au.com.bestnearme.ui.ViewPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_places.*
import androidx.core.os.HandlerCompat.postDelayed
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.ui.adapters.PlacesAdapter
import au.com.bestnearme.ui.utils.MarginItemDecoration
import kotlinx.android.synthetic.main.activity_places.recycler_view_places




class PlacesActivity : BaseActivity(),PlaceView {

    var placePresenterImpl: PlacePresenterImpl?=null

    override fun init(savedInstanceState: Bundle?) {
        recycler_view_places.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = PlacesAdapter(arrayListOf())
        }
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        fetchData()

    }

    override fun setLayout(): Int {

        return R.layout.activity_places;
    }


    override fun onStartScreen() {

    }

    override fun stopScreen() {


    }

    /*private fun setupViewPager(viewPager: ViewPager) {

        mAdapter.addFrag(
            PlacesFragment.newInstance("coffee", application),"coffee")
        mAdapter.addFrag(
            PlacesFragment.newInstance("pizza",application),"pizza")
        mAdapter.addFrag(
            PlacesFragment.newInstance("chicken",application),"chicken")
        viewPager.adapter = mAdapter
    }*/

    override fun showPlaces(places: List<Place>) {

        recycler_view_places.adapter = PlacesAdapter(places)
    }

    override fun showLoading() {
        progress_circular.visibility= View.VISIBLE
    }

    override fun hideLoading() {
        progress_circular.visibility= View.GONE
    }

    override fun loadError(e: Throwable) {

    }

    override fun loadError(msg: String) {

    }

    fun getPresenter(): PlacePresenterImpl?{
        placePresenterImpl = PlacePresenterImpl(this, application)
        return placePresenterImpl
    }

    fun fetchData() {
        getPresenter()?.let {
            it.getPlaces("coffee")
        }
    }

}
