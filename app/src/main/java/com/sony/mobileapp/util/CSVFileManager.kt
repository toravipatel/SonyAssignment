package com.sony.mobileapp.util

import android.content.Context
import com.sony.mobileapp.db.dao.LocaleDao
import com.sony.mobileapp.db.model.LocaleData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


// class to manage CSV file related operation
class CSVFileManager @Inject constructor(@ApplicationContext val appContext: Context, private val localeDao: LocaleDao) {

    /*
    * Function to insert data into Room DB from CSV file
    * */
    fun insertDataIntoRoomDB(fileName:String){
        appContext.resources.assets.open(fileName).bufferedReader().lines().forEach {
            var data = it.split(",")
            localeDao.insert(LocaleData(data[0],data[1],data[2]))
        }
    }
}