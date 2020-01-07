package au.com.bestnearme.views.places

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import au.com.bestnearme.R
import kotlinx.android.synthetic.main.activity_places.*
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.services.CachePrefs
import au.com.bestnearme.views.CommonActivity
import au.com.bestnearme.views.adapters.PlacesAdapter
import au.com.bestnearme.views.placedetails.PlaceDetailsActivity
import au.com.bestnearme.views.utils.Constants.KEY_CATEGORY
import au.com.bestnearme.views.utils.Constants.KEY_PLACE_ID
import au.com.bestnearme.views.utils.Constants.KEY_TRUE_RATING
import au.com.bestnearme.views.utils.loadImage
import au.com.bestnearme.views.utils.showError
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_places.progress_circular
import kotlinx.android.synthetic.main.activity_places.recycler_view_places




class PlacesActivity : CommonActivity() {

    lateinit var mPlacesViewModel: PlacesViewModel
    companion object {
        var mCategoryString: String? = ""
    }

    private val mPlacesAdapter = PlacesAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        mPlacesViewModel = ViewModelProviders.of(this).get(PlacesViewModel::class.java)

        recycler_view_places.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mPlacesAdapter
        }
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        intent.getStringExtra(KEY_CATEGORY)?.let {
            mCategoryString = it
        }

        mCategoryString?.let {
            val category = Gson().fromJson(it,Category::class.java)
            category.apply {
                image_header?.loadImage(heroImageUrl)
                supportActionBar?.title=displayName
                mPlacesViewModel.refresh(category.code, CachePrefs.getLastKnownLatLong(this@PlacesActivity))
            }

        }

        mPlacesAdapter.mOnPlaceClickListener = object : PlacesAdapter.OnPlaceClickListener{
            override fun onPlaceClicked(placeId: String, trueRating: Double) {
                val intent = Intent(this@PlacesActivity,PlaceDetailsActivity::class.java).apply {
                    putExtra(KEY_PLACE_ID, placeId)
                    putExtra(KEY_TRUE_RATING, trueRating)
                }
                startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@PlacesActivity).toBundle())
            }
        }
        observeViewModel()

        invokeLocationAction()

    }

    fun observeViewModel() {
        mPlacesViewModel.mPlaces.observe(this, Observer { places ->
            places?.let {
                recycler_view_places?.visibility = View.VISIBLE
                mPlacesAdapter.updatePlaces(it)
            }
        })

        mPlacesViewModel.mPlacesLoadError.observe(this, Observer { isError ->
              isError?.let { if(it) {
                  mCategoryString?.let {
                      showError(resources.getString(R.string.message_error_places)+" "+
                              Gson().fromJson(it,Category::class.java).displayName,this@PlacesActivity)
                  }

              } }
          })

        mPlacesViewModel.mLoading.observe(this, Observer { isLoading ->
            isLoading?.let { progress_circular?.visibility = if(it) View.VISIBLE else View.GONE

            }

        })
    }

    override fun onRestart() {
        super.onRestart()
        progress_circular?.visibility = View.GONE
    }

    override fun locationEnabled() {

    }


}
