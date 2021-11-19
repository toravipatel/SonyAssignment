package com.sony.mobileapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sony.mobileapp.db.dao.LocaleDao
import com.sony.mobileapp.db.model.LocaleData

// Class to Application Room DB
@Database(entities = [LocaleData::class], version = 1, exportSchema = false)
abstract class SonyDatabase: RoomDatabase() {

    abstract fun localeDao(): LocaleDao

}