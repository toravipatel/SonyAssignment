package com.sony.mobileapp.db.model

import androidx.room.Entity

@Entity(tableName = "LocaleData", primaryKeys = ["languageName", "key"])
class LocaleData(
    var languageName: String,
    var key: String,
    var value:String
)

