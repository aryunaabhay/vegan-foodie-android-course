package vgan.veganfoodie.Entities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by aryuna on 11/6/17.
 */

class DbHelper(ctx: Context): SQLiteOpenHelper(ctx, DbHelper.DB_NAME, null, DbHelper.DB_VERSION) {

    companion object {
        val DB_VERSION = 1
        val DB_NAME = "vgan.db"
        private var instance: DbHelper? = null

        @Synchronized
        fun Instance(context: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
       db?.execSQL(User.TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO: handle upgrades by version
    }
}

