package com.sony.mobileapp.di

import android.content.Context
import androidx.room.Room
import com.sony.mobileapp.db.dao.LocaleDao
import com.sony.mobileapp.db.SonyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



//Hilt module to provide dependency
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    /**
     * Provide method for LocaleDao
     * @param appDatabase is RoomDB
     * @return LocaleDao
     * */
    @Provides
    fun provideChannelDao(appDatabase: SonyDatabase): LocaleDao {
        return appDatabase.localeDao()
    }

    /**
     * Provide method for Room DB
     * @param appContext is ApplicationContext
     * @return RoomDB
     * */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SonyDatabase {
        return Room.databaseBuilder(
            appContext,
            SonyDatabase::class.java,
            "Sony DB"
        ).allowMainThreadQueries().build()
    }
}

