package vgan.veganfoodie.Modules.SignUp

import vgan.veganfoodie.AppDelegate
import vgan.veganfoodie.Interfaces.AppViewModel
import vgan.veganfoodie.R
import vgan.veganfoodie.Utilities.Result
import vgan.veganfoodie.restAPI.UserService

/**
 * Created by aryuna on 10/28/17.
 */

class SignUpViewModel: AppViewModel {
    companion object {
        val emailTxtIdentifier = "emailTxt"
        val passTxtIdentifier = "passTxt"
    }

    fun signup(email: String, password: String, completion:  (result: Result) -> Unit ) {
        var appCtx = AppDelegate.instance.applicationContext
        if(email.isEmpty() || password.isEmpty()) { return completion( Result(false, appCtx.getString(R.string.missing_info_message) ) ) }

        UserService.signUp(email, password, { user -> Unit
            var isSignedUp = user != null
            var message = if (isSignedUp) appCtx.getString(R.string.signup_sucess_message) else appCtx.getString(R.string.signup_incorrect_message)
            AppDelegate.instance.viewModel.user = user
            completion(Result(isSignedUp, message))
        })
    }

}

