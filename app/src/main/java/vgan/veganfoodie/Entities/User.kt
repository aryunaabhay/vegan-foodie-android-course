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
        val emailKey: String = "email"
        val passwordKey: String = "password"

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
            if (pref != null) {
                var prefEditor = pref.edit()
                prefEditor.putString(User.emailKey, email.toLowerCase())
                prefEditor.putString(User.passwordKey, password)
                prefEditor.apply()
                return true
            }
            return false
        }

        fun loginOnSharedPref(email: String, password: String): Boolean{
            var pref = AppDelegate.instance.viewModel.appPref
            if (pref != null) {
                val emailFromPref = pref.getString(emailKey, "")
                val passFromPref = pref.getString(passwordKey, "")
                return emailFromPref == email && passFromPref == password
            }
            return false
        }
    }
}

