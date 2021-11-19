package com.sony.mobileapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sony.mobileapp.repository.LocaleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor(var localeRepository: LocaleRepository): ViewModel(){

    /**
     * Function to insert CSV data into DB
     * */
    fun insertDataIntoDB(){
        localeRepository.insertDataIntoDB()
    }

    /**
    * Function to get Localized value from DB
     * @param language is the selected language
     * @param key is Key to find the value for
    * */
    fun getLocalizedValue(language:String, key:String):String?{
        return localeRepository.getLocalizedValue(language,key)
    }

}