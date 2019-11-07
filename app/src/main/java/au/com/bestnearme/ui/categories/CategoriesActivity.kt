package au.com.bestnearme.ui.categories

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.ui.adapters.CategoriesAdapter
import au.com.bestnearme.ui.adapters.PlacesAdapter
import au.com.bestnearme.ui.utils.MarginItemDecoration
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_places.progress_circular
import kotlinx.android.synthetic.main.activity_places.recycler_view_places




class CategoriesActivity : BaseActivity(),CategoriesView {

    var categoriesPresenterImpl: CategoriesPresenterImpl?=null

    override fun init(savedInstanceState: Bundle?) {
        recycler_view_categories.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = CategoriesAdapter(applicationContext,arrayListOf())
        }
        fetchData()

    }

    override fun setLayout(): Int {

        return R.layout.activity_categories;
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

    override fun showCategories(categories: List<Category>) {
        recycler_view_categories.adapter = CategoriesAdapter(applicationContext,categories)
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

    fun getPresenter(): CategoriesPresenterImpl?{
        categoriesPresenterImpl = CategoriesPresenterImpl(this, application)
        return categoriesPresenterImpl
    }

    fun fetchData() {
        getPresenter()?.let {
            it.getCategories()
        }
    }

}
