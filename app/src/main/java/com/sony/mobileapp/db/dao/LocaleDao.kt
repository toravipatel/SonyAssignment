package com.sony.mobileapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sony.mobileapp.db.model.LocaleData


// Dao to manage CRUD operation over LocaleData Table
@Dao
interface LocaleDao {

    @Insert
    fun insert(localeData: LocaleData)

    @Query("select value from LocaleData where languageName =:language and `key`=:key")
    fun getValue(language:String, key:String):String

}