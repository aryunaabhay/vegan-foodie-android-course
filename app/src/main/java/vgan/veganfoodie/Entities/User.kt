package vgan.veganfoodie.Entities

import vgan.veganfoodie.AppDelegate
import vgan.veganfoodie.Utilities.PersistanceType

/**
 * Created by aryuna on 10/27/17.
 */
class User {
    var id: Int = 0
    var email: String = ""
    var password: String = ""


    companion object {
        fun signUp(email: String, password: String): Boolean {
            when (AppDelegate.instance.viewModel.persistanceType) {
                PersistanceType.SharedPref -> return User.signUpOnSharedPref(email, password)
                else -> return User.signUpOnSharedPref(email, password)
            }
        }
        fun login(email: String, password: String): Boolean {
            when (AppDelegate.instance.viewModel.persistanceType) {
                PersistanceType.SharedPref -> return User.loginOnSharedPref(email, password)
                else -> return User.loginOnSharedPref(email, password)
            }
        }

        //Shared Preferences
        fun signUpOnSharedPref(email: String, password: String): Boolean{
            var pref = AppDelegate.instance.viewModel.appPref
            var result = false
            if (pref != null) {
                var prefEditor = pref.edit()
                prefEditor.putString("username", email)
                prefEditor.putString("pass", password)
                val commitResult = prefEditor.commit()
                result = commitResult
            }
            return result
        }

        fun loginOnSharedPref(email: String, password: String): Boolean{
            var pref = AppDelegate.instance.viewModel.appPref
            var result = false
            if (pref != null) {
                val emailFromPref = pref.getString("username", "")
                val passFromPref = pref.getString("pass", "")
                val loginResult = emailFromPref == email && passFromPref == password
                result = loginResult
            }
            return result
        }
    }
}

