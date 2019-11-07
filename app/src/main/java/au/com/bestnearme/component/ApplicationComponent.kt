package au.com.bestnearme.component

import au.com.bestnearme.MyApplication
import au.com.bestnearme.di.module.AppModule
import au.com.bestnearme.di.module.NetModule
import au.com.bestnearme.ui.categories.CategoriesPresenterImpl
import au.com.bestnearme.ui.login.LoginPresenterImpl
import au.com.bestnearme.ui.places.PlacePresenterImpl
import au.com.bestnearme.ui.posts.PostPresenterImpl
import dagger.Component

@Component(modules = [AppModule::class, NetModule::class])
interface ApplicationComponent {

    fun inject(mewApplication: MyApplication)
    fun inject(mLoginPresenterImpl: LoginPresenterImpl)
    fun inject(mPostPresenterImpl: PostPresenterImpl)
    fun inject(mPlacePresenterImpl: PlacePresenterImpl)
    fun inject(mCategoriesPresenterImpl: CategoriesPresenterImpl)

}