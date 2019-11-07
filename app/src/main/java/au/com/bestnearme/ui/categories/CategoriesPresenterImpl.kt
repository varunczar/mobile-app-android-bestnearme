package au.com.bestnearme.ui.categories

import android.app.Application
import au.com.bestnearme.MyApplication
import au.com.bestnearme.di.network.INetworkApi
import au.com.bestnearme.ui.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class CategoriesPresenterImpl(var categoriesView: CategoriesView, var applicationComponent: Application) : CategoriesPresenter,
    Presenter<CategoriesView>(categoriesView) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as MyApplication).applicationComponent.inject(this)
    }

    override fun getCategories() {


        var view=view()

        view?.let {
            it.showLoading()
            var allCategories = mNetworkApi.getCategories()
            addDisposable(allCategories.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                view?.let {
                                    it.hideLoading()
                                    categoriesView.showCategories(result)

                                }
                            },
                            { error ->
                                view?.let {
                                    it.hideLoading()
                                }
                            }
                    ) ) }

        var allCategories = mNetworkApi.getCategories()
        allCategories.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    categoriesView.showCategories(it)
                },{ error ->
                    view?.let {
                        it.hideLoading()
                    }
                })

    }


}