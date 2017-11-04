package vgan.veganfoodie.Modules.Login

import android.content.Context
import vgan.veganfoodie.Entities.AppState
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

    fun login(email: String, password: String, ctx: Context): Result {
        if(email== "" || password == "") { return Result(false,  ctx.resources.getString(R.string.missing_info_message) ) }
        val loggedIn = User.login(email, password, ctx)
        if (loggedIn) {
            var loggeUser = User()
            loggeUser.email = email
            loggeUser.password = password
            AppState.user = loggeUser
        }
        var message = if(loggedIn) ctx.resources.getString(R.string.login_sucess_message) + email else ctx.resources.getString(R.string.login_incorrect_message)
        return Result(loggedIn, message)
    }
}