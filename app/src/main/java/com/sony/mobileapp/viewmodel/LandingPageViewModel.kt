package com.sony.mobileapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sony.mobileapp.model.FileIOResult
import com.sony.mobileapp.repository.LocaleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor(var localeRepository: LocaleRepository): ViewModel(){


    private var fileIOUpdate: MutableLiveData<FileIOResult> = MutableLiveData()

    fun getFileIOUpdate() = fileIOUpdate

    /**
     * Function to insert CSV data into DB
     * */
    fun insertDataIntoDB(){

        fileIOUpdate.value = FileIOResult.Loading()
        viewModelScope.launch{
            localeRepository.insertDataIntoDB()
            withContext(Dispatchers.Main){
                fileIOUpdate.value = FileIOResult.Success()
            }
        }

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