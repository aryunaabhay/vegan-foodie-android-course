package vgan.veganfoodie.Modules.SignUp

import android.content.Context
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

    fun signup(email: String, password: String, ctx: Context): Result {
        if(email== "" || password == "") { return  Result(false, ctx.resources.getString(R.string.missing_info_message) ) }
        var isSignedUp = User.signUp(email, password, ctx)
        var message = if (isSignedUp)  ctx.resources.getString(R.string.signup_sucess_message) else  ctx.resources.getString(R.string.signup_incorrect_message)
        return Result(isSignedUp, message)
    }

}

