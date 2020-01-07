package au.com.bestnearme.views.placedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.bestnearme.component.DaggerApplicationComponent
import au.com.bestnearme.data.placedetails.PlaceDetails
import au.com.bestnearme.di.network.INetworkApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlaceDetailsViewModel : ViewModel() {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val mPlaceDetails  = MutableLiveData<PlaceDetails>()
    val mPlaceDetailsLoadError  = MutableLiveData<Boolean>()
    val mLoading  = MutableLiveData<Boolean>()

    fun refresh(placeId : String) {
        fetchPlaceDetailsForId(placeId)
    }

    private fun fetchPlaceDetailsForId(placeId : String) {
        mLoading.value=true
        disposable.add(
            mNetworkApi.getPlaceDetailsForId(placeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PlaceDetails>() {
                    override fun onSuccess(value: PlaceDetails) {
                        mPlaceDetails.value = value
                        mPlaceDetailsLoadError.value = false
                        mLoading.value = false
                    }

                    override fun onError(throwable: Throwable) {
                        throwable.printStackTrace()
                        mPlaceDetailsLoadError.value = true
                        mLoading.value = false
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}