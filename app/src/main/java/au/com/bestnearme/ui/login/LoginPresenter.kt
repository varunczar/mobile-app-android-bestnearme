package au.com.bestnearme.ui.login

interface LoginPresenter {

    fun peformLogin(userName: String, userPassword: String)

    fun validateUser(userName: String, userPassword: String)
}