package vgan.veganfoodie.Modules.SignUp

import vgan.veganfoodie.AppDelegate
import vgan.veganfoodie.Entities.User
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.R
import vgan.veganfoodie.Utilities.Result

/**
 * Created by aryuna on 10/28/17.
 */

class SignUpViewModel: ViewModel {
    companion object {
        val emailTxtIdentifier = "emailTxt"
        val passTxtIdentifier = "passTxt"
    }

    fun signup(email: String, password: String): Result {
        var appCtx = AppDelegate.instance.applicationContext
        if(email== "" || password == "") { return  Result(false, appCtx.resources.getString(R.string.missing_info_message) ) }
        var isSignedUp = User.signUp(email, password)
        var message = if (isSignedUp) appCtx.resources.getString(R.string.signup_sucess_message) else appCtx.resources.getString(R.string.signup_incorrect_message)
        return Result(isSignedUp, message)
    }

}

