package vgan.veganfoodie.Entities

import android.content.ContentValues
import android.database.Cursor
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
        val TABLE_KEY = "Users"
        val EMAIL_KEY = "email"
        val PASSWORD_KEY = "password"
        val TABLE_SQL = "CREATE TABLE IF NOT EXISTS "+ User.TABLE_KEY +"(id integer PRIMARY KEY AUTOINCREMENT, "+ User.EMAIL_KEY +" VARCHAR, "+ User.PASSWORD_KEY +" VARCHAR);"

        fun signUp(email: String, password: String): Boolean {
            when (AppDelegate.instance.viewModel.persistanceType) {
                PersistanceType.SharedPref -> return User.signUpOnSharedPref(email, password)
                PersistanceType.Sqlite -> return User.signUpOnSqlite(email, password)
                PersistanceType.Realm -> return false
            }
        }
        fun login(email: String, password: String): Boolean {
            when (AppDelegate.instance.viewModel.persistanceType) {
                PersistanceType.SharedPref -> return User.loginOnSharedPref(email, password)
                PersistanceType.Sqlite -> return User.loginOnSqlite(email, password)
                PersistanceType.Realm -> return false
            }
        }

        //SQLITE
        fun signUpOnSqlite(email: String, password: String): Boolean {
            val dbHelper = AppDelegate.instance.viewModel.dbHelper
            var values = ContentValues()
            values.put(User.EMAIL_KEY, email)
            values.put(User.PASSWORD_KEY, password)
            dbHelper?.writableDatabase?.insert(User.TABLE_KEY, null, values)
            dbHelper?.close()
            return true
        }

        fun loginOnSqlite(email: String, password: String): Boolean {
            val dbHelper = AppDelegate.instance.viewModel.dbHelper
            var cursor: Cursor? = null
            try {
                cursor = dbHelper?.readableDatabase?.query(User.TABLE_KEY, arrayOf("*"), User.EMAIL_KEY + " = ? AND "+ User.PASSWORD_KEY +" = ?", arrayOf(email, password), null, null, null, "1")
                return cursor != null && cursor.moveToFirst()
            } finally {
                if (cursor != null) {
                   cursor.close()
                }
                dbHelper?.close()
            }
        }

        //Shared Preferences
        fun signUpOnSharedPref(email: String, password: String): Boolean{
            var pref = AppDelegate.instance.viewModel.appPref
            if (pref != null) {
                var prefEditor = pref.edit()
                prefEditor.putString(User.EMAIL_KEY, email.toLowerCase())
                prefEditor.putString(User.PASSWORD_KEY, password)
                prefEditor.apply()
                return true
            }
            return false
        }

        fun loginOnSharedPref(email: String, password: String): Boolean{
            var pref = AppDelegate.instance.viewModel.appPref
            if (pref != null) {
                val emailFromPref = pref.getString(EMAIL_KEY, "")
                val passFromPref = pref.getString(PASSWORD_KEY, "")
                return emailFromPref == email && passFromPref == password
            }
            return false
        }
    }
}