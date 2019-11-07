package au.com.bestnearme.ui.places

import android.app.Application
import au.com.bestnearme.MyApplication
import au.com.bestnearme.di.network.INetworkApi
import au.com.bestnearme.ui.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class PlacePresenterImpl(var placeView: PlaceView, var applicationComponent: Application) : PlacePresenter,
    Presenter<PlaceView>(placeView) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as MyApplication).applicationComponent.inject(this)
    }

    override fun getPlaces(category : String) {


        var view=view()

        view?.let {
            it.showLoading()
            var allPlaces = mNetworkApi.getPlaces(category)
            addDisposable(allPlaces.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                view?.let {
                                    it.hideLoading()
                                    placeView.showPlaces(result)

                                }
                            },
                            { error ->
                                view?.let {
                                    it.hideLoading()
                                }
                            }
                    ) ) }

        var allPlaces = mNetworkApi.getPlaces(category)
        allPlaces.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    placeView.showPlaces(it)
                },{ error ->
                    view?.let {
                        it.hideLoading()
                    }
                })

    }


}