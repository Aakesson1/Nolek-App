package com.example.nolekapp.Database.Data

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.Date


class TestResultat: RealmObject{
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var ownerId: String = ""
    var description: String= ""
    var name: String = ""
    var objectType: String = ""
    var reason: String = ""
    var sniffingPoint: String = ""
    var status: String = ""
    //var timestamp: Date? = null
}
