package com.sony.mobileapp.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sony.mobileapp.viewmodel.LandingPageViewModel
import com.sony.mobileapp.util.LocaleHelper
import com.sony.mobileapp.R
import com.sony.mobileapp.model.FileIOResult
import com.sony.mobileapp.util.Constant
import com.sony.mobileapp.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var messageView:TextView
    private lateinit var firstNameTV:TextView
    private lateinit var lastNameTV:TextView
    private lateinit var btnHindi:Button
    private lateinit var btnEnglish:Button
    private lateinit var btnChinese:Button
    private var selectedLanguage = Constant.LANGUAGE_ENGLISH
    private var progressDialog: LoadingDialog? = null


    private val landingPageViewModel: LandingPageViewModel by viewModels()
    @Inject lateinit var localeHelper: LocaleHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initObserver()
        landingPageViewModel.insertDataIntoDB()
        setListener()
    }

    private fun initObserver() {
        landingPageViewModel.getFileIOUpdate().observe(this) {

            when (it) {
                is FileIOResult.Success -> {
                    progressDialog?.hideProgressdialog()
                }
                is FileIOResult.Loading -> {
                    progressDialog?.showProgressDialog()
                }
                is FileIOResult.Error -> {
                }
            }
        }
    }

    /*
    * Function to set listener
    * */
    private fun setListener() {
        btnEnglish.setOnClickListener {
            selectedLanguage = Constant.LANGUAGE_ENGLISH
            refreshUIlabels()
        }

        btnHindi.setOnClickListener {
            selectedLanguage = Constant.LANGUAGE_HINDI
            refreshUIlabels()
        }

        btnChinese.setOnClickListener {
            selectedLanguage = Constant.LANGUAGE_CHINESE
            refreshUIlabels()
        }
    }

    /*
    * Function to init component
    * */
    private fun initComponent() {
        progressDialog = LoadingDialog(this)
        messageView = findViewById(R.id.textView)
        firstNameTV = findViewById(R.id.firstNameTV)
        lastNameTV = findViewById(R.id.lastNameTV)
        btnHindi = findViewById(R.id.btnHindi)
        btnEnglish = findViewById(R.id.btnEnglish)
        btnChinese = findViewById(R.id.btnChinese)
    }

    /*
    * Function to set data on View
    * */
    private fun setDataOnUI(view: TextView,value:String?, defaultValue:String){
        value?.let {
            view.text = it
        } ?: kotlin.run {
            view.text = defaultValue
        }
    }

    /*
    * Function to refresh all UI labels based on selected language
    * */
    private fun refreshUIlabels(){

        context = localeHelper.setLocale(selectedLanguage)
        setDataOnUI(messageView,landingPageViewModel.getLocalizedValue(selectedLanguage,"key1"), context.getString(
            R.string.hello_world
        ))
        setDataOnUI(firstNameTV,landingPageViewModel.getLocalizedValue(selectedLanguage,"first_name"), context.getString(
            R.string.first_name
        ))
        setDataOnUI(lastNameTV,landingPageViewModel.getLocalizedValue(selectedLanguage,"last_name"), context.getString(
            R.string.last_name
        ))
    }
}