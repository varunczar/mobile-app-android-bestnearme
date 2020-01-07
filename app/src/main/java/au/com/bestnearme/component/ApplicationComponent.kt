package au.com.bestnearme.component

import au.com.bestnearme.LauncherViewModel
import au.com.bestnearme.MyApplication
import au.com.bestnearme.di.module.AppModule
import au.com.bestnearme.di.module.NetModule
import au.com.bestnearme.views.categories.CategoriesViewModel
import au.com.bestnearme.views.intro.IntroViewModel
import au.com.bestnearme.views.placedetails.PlaceDetailsViewModel
import au.com.bestnearme.views.places.PlacesViewModel
import dagger.Component

@Component(modules = [AppModule::class, NetModule::class])
interface ApplicationComponent {

    fun inject(mewApplication: MyApplication)
    fun inject(categoriesViewModel: CategoriesViewModel)
    fun inject(placesViewModel: PlacesViewModel)
    fun inject(placeDetailsViewModel: PlaceDetailsViewModel)
    fun inject(introViewModel: IntroViewModel)
    fun inject(launcherViewModel: LauncherViewModel)

}