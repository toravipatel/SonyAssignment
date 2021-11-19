package com.sony.mobileapp.repository

import com.sony.mobileapp.db.dao.LocaleDao
import com.sony.mobileapp.util.CSVFileManager
import javax.inject.Inject

//Repository class to provide data to viewmodel layer
class LocaleRepository @Inject constructor(val csvFileManager: CSVFileManager, private val localeDao: LocaleDao) {

    //Function to insert data into room DB
    fun insertDataIntoDB(){
        csvFileManager.insertDataIntoRoomDB("localization.csv")
    }

    //Function to get data from room DB based on given language and key
    fun getLocalizedValue(language: String, key: String):String? {
        return localeDao.getValue(language,key)
    }

}