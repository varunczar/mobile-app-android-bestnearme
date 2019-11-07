package au.com.bestnearme.ui.login

import android.widget.EditText
import au.com.bestnearme.ui.IView

interface LoginView: IView {

    fun navigateToHome()

    fun onBackPress()

    fun onPasswordError()
}