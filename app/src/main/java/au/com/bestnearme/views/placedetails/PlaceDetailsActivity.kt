package au.com.bestnearme.views.placedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.bestnearme.R
import au.com.bestnearme.views.adapters.PlaceDetailsAdapter
import au.com.bestnearme.views.utils.Constants.KEY_PLACE_ID
import au.com.bestnearme.views.utils.Constants.KEY_TRUE_RATING
import au.com.bestnearme.views.utils.getPlaceDetailsElements
import au.com.bestnearme.views.utils.showError
import kotlinx.android.synthetic.main.activity_place_details.*
import kotlinx.android.synthetic.main.activity_places.*

class PlaceDetailsActivity : AppCompatActivity() {

    lateinit var mPlaceDetailsViewModel: PlaceDetailsViewModel
    private val mPlaceDetailsAdapter = PlaceDetailsAdapter()
    var mTrueRating = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_details)
        setSupportActionBar(toolbar_place_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        mPlaceDetailsViewModel = ViewModelProviders.of(this).get(PlaceDetailsViewModel::class.java)


        recycler_view_placedetails.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mPlaceDetailsAdapter
        }

        val placeId = intent.getStringExtra(KEY_PLACE_ID)
        mTrueRating = intent.getDoubleExtra(KEY_TRUE_RATING,0.0)
        placeId?.let {
            mPlaceDetailsViewModel.refresh(placeId)
        }

        observeViewModel()


    }

    fun observeViewModel() {
        mPlaceDetailsViewModel.mPlaceDetails.observe(this, Observer { places ->
            places?.let {
                recycler_view_places?.visibility = View.VISIBLE
                mPlaceDetailsAdapter.updateSections(it.getPlaceDetailsElements(),
                    it,
                    mTrueRating)
            }
        })

        mPlaceDetailsViewModel.mPlaceDetailsLoadError.observe(this, Observer { isError ->
              isError?.let {
                  if(it) {
                      showError(
                          resources.getString(R.string.message_error_placedetails),
                          this@PlaceDetailsActivity
                      )
                  }}
          })

        mPlaceDetailsViewModel.mLoading.observe(this, Observer { isLoading ->
            isLoading?.let { progress_circular_place_details?.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    //list_error.visibility = View.GONE
                }
            }

        })
    }


    override fun onRestart() {
        super.onRestart()
        progress_circular_place_details?.visibility = View.GONE
    }

}
