package au.com.bestnearme.views.places

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.bestnearme.component.DaggerApplicationComponent
import au.com.bestnearme.data.location.LocationModel
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.di.network.INetworkApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlacesViewModel : ViewModel() {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val mPlaces  = MutableLiveData<List<Place>>()
    val mPlacesLoadError  = MutableLiveData<Boolean>()
    val mLoading  = MutableLiveData<Boolean>()

    init {
        mLoading.value=false
    }

    fun refresh(categoryCode : String, location : LocationModel?) {
        mLoading.value = false
        fetchPlacesForCategory(categoryCode, location)
    }

    private fun fetchPlacesForCategory(categoryCode : String, location: LocationModel?) {
        location?.let {
            mLoading.value=true
            disposable.add(
                mNetworkApi.getPlaces(it.latitude,it.longitude,categoryCode)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<List<Place>>() {
                        override fun onSuccess(value: List<Place>) {
                            mPlaces.value = value
                            mPlacesLoadError.value = false
                            mLoading.value = false
                        }

                        override fun onError(throwable: Throwable) {
                            throwable.printStackTrace()
                            mPlacesLoadError.value = true
                            mLoading.value = false
                        }
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}