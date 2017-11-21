package vgan.veganfoodie.Entities

import android.content.ContentValues
import android.database.Cursor
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import vgan.veganfoodie.AppDelegate
import vgan.veganfoodie.Networking.HTTPVerb
import vgan.veganfoodie.Networking.Networking
import vgan.veganfoodie.Utilities.PersistanceType


/**
 * Created by aryuna on 10/27/17.
 */
open class User: RealmObject() {
    @PrimaryKey var id: Int = 0
    @Required var email: String = ""
    @Required var password: String = ""
    @SerializedName("body")
    @Required var description: String = ""

    companion object {
        val TABLE_KEY = "Users"
        val EMAIL_KEY = "email"
        val PASSWORD_KEY = "password"
        val TABLE_SQL = "CREATE TABLE IF NOT EXISTS "+ User.TABLE_KEY +"(id integer PRIMARY KEY AUTOINCREMENT, "+ User.EMAIL_KEY +" VARCHAR, "+ User.PASSWORD_KEY +" VARCHAR);"

        fun signUp(email: String, password: String): Boolean {
            when (AppDelegate.instance.viewModel.persistanceType) {
                PersistanceType.SharedPref -> return User.signUpOnSharedPref(email, password)
                PersistanceType.Sqlite -> return User.signUpOnSqlite(email, password)
                PersistanceType.Realm -> return User.signUpOnRealm(email, password)
            }
        }
        fun login(email: String, password: String): Boolean {
            when (AppDelegate.instance.viewModel.persistanceType) {
                PersistanceType.SharedPref -> return User.loginOnSharedPref(email, password)
                PersistanceType.Sqlite -> return User.loginOnSqlite(email, password)
                PersistanceType.Realm -> return User.loginOnRealm(email, password)
            }
        }

        fun loginOnServer(email: String, password: String, completion: (user: User?) -> Unit ) {
            Networking.request(HTTPVerb.GET, "https://jsonplaceholder.typicode.com/posts/1", { json -> Unit
                if (json != null) {
                    val user = Gson().fromJson(json, User::class.java) as? User
                    completion(user)
                }else{
                    completion(null)
                }
            })
        }

        //REALM
        fun signUpOnRealm(email: String, password: String): Boolean {
            val realm = Realm.getDefaultInstance()
            try {
                realm.executeTransaction {
                    var user = realm.createObject(User::class.java, 20)
                    user.email = email
                    user.password = password
                }
            } finally {
                realm.close()
            }
            return true
        }

        fun loginOnRealm(email: String, password: String): Boolean{
            val realm = Realm.getDefaultInstance()
            var user: User? = null
            try {
                realm.executeTransaction {
                    user = realm.where(User::class.java).equalTo(User.EMAIL_KEY, email).equalTo(User.PASSWORD_KEY, password).findFirst()
                }
            } finally {
                realm.close();
            }
            return user != null
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