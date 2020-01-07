package au.com.bestnearme.views.categories

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import au.com.bestnearme.R
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.views.CommonActivity
import au.com.bestnearme.views.adapters.CategoriesAdapter
import au.com.bestnearme.views.places.PlacesActivity
import au.com.bestnearme.views.utils.Constants.KEY_CATEGORY
import au.com.bestnearme.views.utils.showError
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_places.progress_circular


class CategoriesActivity : CommonActivity() {

    lateinit var mCategoriesViewModel: CategoriesViewModel
    private val mCategoriesAdapter = CategoriesAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        mCategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        mCategoriesViewModel.refresh()

        recycler_view_categories.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = mCategoriesAdapter
        }

        observeViewModel()

        mCategoriesAdapter.mOnCategoryClickListener = object : CategoriesAdapter.OnCategoryClickListener{
            override fun onCategoryClicked(category: Category) {
                val intent = Intent(this@CategoriesActivity,PlacesActivity::class.java).apply {
                    putExtra(KEY_CATEGORY, Gson().toJson(category))
                }
                startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@CategoriesActivity).toBundle())
            }
        }
        invokeLocationAction()
    }

    fun observeViewModel() {
        mCategoriesViewModel.categories.observe(this, Observer { categories ->
            categories?.let {
                recycler_view_categories?.visibility = View.VISIBLE
                mCategoriesAdapter.updateCategories(it.sortedBy { it.id }) }
        })

        mCategoriesViewModel.categoriesLoadError.observe(this, Observer { isError ->
            isError?.let {
                if(it) {
                    showError(
                        resources.getString(R.string.message_error_categories),
                        this@CategoriesActivity
                    )
                }}
        })

        mCategoriesViewModel.loading.observe(this, Observer { isLoading ->
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
