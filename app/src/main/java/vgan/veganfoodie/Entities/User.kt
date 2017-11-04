package vgan.veganfoodie.Entities

import android.content.Context

/**
 * Created by aryuna on 10/27/17.
 */
class User {
    var id: Int = 0
    var email: String = ""
    var password: String = ""

    companion object {
        fun signUp(email: String, password: String, ctx: Context): Boolean {
            val safeCtx = ctx.applicationContext
            var pref = safeCtx.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
            var prefEditor = pref.edit()
            prefEditor.putString("username", email)
            prefEditor.putString("pass", password)
            prefEditor.apply()
            return true
        }

        fun login(email: String, password: String, ctx: Context): Boolean {
            val safeCtx = ctx.applicationContext
            var pref = safeCtx.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
            var emailFromPref = pref.getString("username", "")
            var passFromPref = pref.getString("pass", "")

            var result = emailFromPref == email && passFromPref == password
            return result
        }
    }
}

