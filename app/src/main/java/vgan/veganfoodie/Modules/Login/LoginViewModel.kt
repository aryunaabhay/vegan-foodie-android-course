package vgan.veganfoodie.Modules.Login

import vgan.veganfoodie.AppDelegate
import vgan.veganfoodie.Interfaces.AppViewModel
import vgan.veganfoodie.R
import vgan.veganfoodie.Utilities.Result
import vgan.veganfoodie.restAPI.UserService

/**
 * Created by aryuna on 10/18/17.
 */


class LoginViewModel: AppViewModel {
    companion object {
        val emailTxtIdentifier = "emailTxt"
        val passTxtIdentifier = "passTxt"
    }

    fun login(email: String, password: String, completion: (result: Result) -> Unit ) {
        var appCtx = AppDelegate.instance.applicationContext
        if(email.isEmpty() || password.isEmpty()) {  completion( Result(false, appCtx.getString(R.string.missing_info_message) ) ) }

        UserService.login(email, password, { user -> Unit
            val loggedIn = user != null
            var message = if(loggedIn) appCtx.getString(R.string.login_sucess_message, email) else appCtx.getString(R.string.login_incorrect_message)
            AppDelegate.instance.viewModel.user = user
            completion(Result(loggedIn, message))
        })
    }
}