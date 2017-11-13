package vgan.veganfoodie

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteOpenHelper
import vgan.veganfoodie.Entities.DbHelper
import vgan.veganfoodie.Entities.User
import vgan.veganfoodie.Interfaces.ViewModel
import vgan.veganfoodie.Utilities.PersistanceType

/**
 * Created by aryuna on 11/4/17.
 */
class AppDelegate: Application() {
    var viewModel = AppViewModel()

    override fun onCreate() {
        super.onCreate()
        instance = this
        instance.viewModel.setUp(this.applicationContext)

    }
    companion object {
        lateinit var instance: AppDelegate
            private set
    }
}

class AppViewModel: ViewModel {
    var persistanceType: PersistanceType = PersistanceType.Sqlite
    val preferencesId = "AppPreferences"
    var user: User? = null
    var appPref: SharedPreferences? = null
    var dbHelper: SQLiteOpenHelper? = null

    fun setUp(ctx: Context){
        val safeCtx = ctx.applicationContext
        this.appPref = safeCtx.getSharedPreferences(this.preferencesId, Context.MODE_PRIVATE)
        this.dbHelper = DbHelper.Instance(safeCtx)
    }
}