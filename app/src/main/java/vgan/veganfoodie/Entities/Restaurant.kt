package vgan.veganfoodie.Entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

/**
 * Created by aryuna on 11/19/17.
 */

open class Restaurant: RealmObject() {
    @PrimaryKey
    var id: Int = 0
    @Required
    var name: String = ""
    @Required
    var specialty: String = ""
    var lat: Double = 0.0
    var long: Double = 0.0
}