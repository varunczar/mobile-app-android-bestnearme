package au.com.bestnearme.views.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.bestnearme.component.DaggerApplicationComponent
import au.com.bestnearme.data.IntroFragmentComponent
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.views.utils.Constants
import io.reactivex.disposables.CompositeDisposable

class IntroViewModel : ViewModel() {

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val mIntroComponents  = MutableLiveData<List<IntroFragmentComponent>>()
    val mShowTurnLocationButton = MutableLiveData<Boolean>()

    init {
        mShowTurnLocationButton.value=false
    }

    fun fetchComponents() {
        mIntroComponents.value = Constants.INTRO_SCREEN_OBJECTS
    }

    fun pageChanged(pageNumber : Int) {
        mIntroComponents.value?.let {
            mShowTurnLocationButton.value = false
            if(pageNumber == it.size - 1) {
                mShowTurnLocationButton.value = true
            }
        }  ?: run {
            mShowTurnLocationButton.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}