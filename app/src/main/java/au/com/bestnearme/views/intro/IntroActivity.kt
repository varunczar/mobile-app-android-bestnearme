package au.com.bestnearme.views.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import au.com.bestnearme.R
import au.com.bestnearme.views.CommonActivity
import au.com.bestnearme.views.adapters.IntroPagerAdapter
import au.com.bestnearme.views.categories.CategoriesViewModel
import au.com.bestnearme.views.utils.FadePageTransformer
import au.com.bestnearme.views.utils.GpsUtils
import kotlinx.android.synthetic.main.activity_intro.*
import android.app.Activity
import android.content.Intent
import au.com.bestnearme.services.CachePrefs
import au.com.bestnearme.views.categories.CategoriesActivity


class IntroActivity : CommonActivity() {

    lateinit var mIntroViewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        mIntroViewModel = ViewModelProviders.of(this).get(IntroViewModel::class.java)
        mIntroViewModel.fetchComponents()

        button_turn_on_location?.setOnClickListener {
            invokeLocationAction()
        }

        view_pager.setPageTransformer(false, FadePageTransformer())

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageSelected(position: Int) {
                mIntroViewModel.pageChanged(position)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        observeViewModels()
    }

    fun observeViewModels() {
        mIntroViewModel.mIntroComponents.observe(this, Observer { introComponents ->
            introComponents?.let {
                view_pager.adapter = IntroPagerAdapter(this@IntroActivity, it)
                indicator?.setViewPager(view_pager)
            }
        })

        mIntroViewModel.mShowTurnLocationButton.observe(this, Observer { showTurnLocationButton ->
            showTurnLocationButton?.let { if(it)
                button_turn_on_location?.visibility = View.VISIBLE else button_turn_on_location?.visibility = View.INVISIBLE }
        })

    }

    override fun locationEnabled() {
        CachePrefs.setIntroShown(true,this)
        startActivity(Intent(this, CategoriesActivity::class.java))
        finish()
    }
}
