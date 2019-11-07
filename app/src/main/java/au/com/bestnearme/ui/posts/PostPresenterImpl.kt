package au.com.bestnearme.ui.posts

import android.app.Application
import au.com.bestnearme.MyApplication
import au.com.bestnearme.di.network.INetworkApi
import au.com.bestnearme.ui.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class PostPresenterImpl(var postView: PostView, var applicationComponent: Application) : PostPresenter,
    Presenter<PostView>(postView) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as MyApplication).applicationComponent.inject(this)
    }

    override fun getAllPosts() {


        var view=view()

        view?.let {
            it.showLoading()
            var allPosts = mNetworkApi.getAllPosts()
            addDisposable(allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                view?.let {
                                    it.hideLoading()
                                    postView.showAllPosts(result)

                                }
                            },
                            { error ->
                                view?.let {
                                    it.hideLoading()
                                }
                            }
                    ) ) }

        var allPosts = mNetworkApi.getAllPosts()
        allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    postView.showAllPosts(it)
                }

    }


}