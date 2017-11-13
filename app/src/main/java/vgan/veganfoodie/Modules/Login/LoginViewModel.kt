package vgan.veganfoodie.Modules.Login

import vgan.veganfoodie.AppDelegate
import vgan.veganfoodie.Entities.User
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.R
import vgan.veganfoodie.Utilities.Result

/**
 * Created by aryuna on 10/18/17.
 */


class LoginViewModel: ViewModel {
    companion object {
        val emailTxtIdentifier = "emailTxt"
        val passTxtIdentifier = "passTxt"
    }

    fun login(email: String, password: String): Result {
        var appCtx = AppDelegate.instance.applicationContext
        if(email.isEmpty() || password.isEmpty()) { return Result(false, appCtx.getString(R.string.missing_info_message) ) }
        val loggedIn = User.login(email, password)
        if (loggedIn) {
            var loggeUser = User()
            loggeUser.email = email
            loggeUser.password = password
            AppDelegate.instance.viewModel.user = loggeUser
        }
        var message = if(loggedIn) appCtx.getString(R.string.login_sucess_message, email) else appCtx.getString(R.string.login_incorrect_message)
        return Result(loggedIn, message)
    }
}