package vgan.veganfoodie.Modules.Login

import vgan.veganfoodie.Entities.AppState
import vgan.veganfoodie.Entities.User
import vgan.veganfoodie.Interfaces.ViewModel

/**
 * Created by aryuna on 10/18/17.
 */


class LoginViewModel: ViewModel {
    companion object {
        val emailTxtIdentifier = "emailTxt"
        val passTxtIdentifier = "passTxt"
    }

    fun login(email: String, password: String): Boolean {
        val loggedIn = email == "aryunadas@gmail.com" && password == "vegnfoodie2017"
        if (loggedIn) {
            var loggeUser = User()
            loggeUser.email = email
            loggeUser.password = password
            AppState.user = loggeUser
        }
        return loggedIn
    }
}