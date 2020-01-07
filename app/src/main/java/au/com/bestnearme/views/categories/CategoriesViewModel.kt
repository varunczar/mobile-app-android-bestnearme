package au.com.bestnearme.views.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.bestnearme.component.DaggerApplicationComponent
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.di.network.INetworkApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoriesViewModel : ViewModel() {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    private val disposable = CompositeDisposable()

    val categories  = MutableLiveData<List<Category>>()
    val categoriesLoadError  = MutableLiveData<Boolean>()
    val loading  = MutableLiveData<Boolean>()

    init {
        DaggerApplicationComponent.create().inject(this)
        loading.value=false
    }


    fun refresh() {
        fetchCategories()
    }

    private fun fetchCategories() {
        loading.value=true
        disposable.add(
            mNetworkApi.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Category>>() {
                    override fun onSuccess(value: List<Category>) {
                        categories.value = value
                        categoriesLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(throwable: Throwable) {
                        throwable.printStackTrace()
                        categoriesLoadError.value = true
                        loading.value = false
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}