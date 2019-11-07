package au.com.bestnearme.ui.login

import android.app.Application
import au.com.bestnearme.MyApplication
import au.com.bestnearme.di.network.INetworkApi
import au.com.bestnearme.ui.Presenter

import javax.inject.Inject

class LoginPresenterImpl(var loginViewInit: LoginView, var mApplication: Application) : LoginPresenter,
    Presenter<LoginView>(loginViewInit) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (mApplication as MyApplication).applicationComponent.inject(this)
    }

    override fun validateUser(userName: String, userPassword: String) {

    }

    override fun peformLogin(userName: String, userPassword: String) {
        if (userName == "hammad") {
            loginViewInit.navigateToHome()
        }
    }
}