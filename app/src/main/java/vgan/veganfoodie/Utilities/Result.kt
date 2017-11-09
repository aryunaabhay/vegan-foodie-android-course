package vgan.veganfoodie.Utilities

/**
 * Created by aryuna on 11/4/17.
 */
class Result(var state: Boolean, var message: String) {

}

enum class PersistanceType {
    SharedPref, Sqlite, Realm
}
