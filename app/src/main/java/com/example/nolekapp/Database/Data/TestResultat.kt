package com.example.nolekapp.Database.Data

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class TestResultat : RealmObject{
    @PrimaryKey var serienumberId: Int = 0
    var name: String = ""
    var description: String = ""
    var sniffingPoint: String = ""
    var objectType: String = ""
    var reason: String = ""
    var status: String = ""
    var timestamp: RealmInstant = RealmInstant.now()
}

