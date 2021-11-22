package com.sony.mobileapp.model

/*
* Seale class for API Result
* */
sealed class FileIOResult(
    val message: String? = null
) {

    // For Success
    class Success(message: String? = null) : FileIOResult(message)

    // For Error
    class Error(message: String?) : FileIOResult( message)

    // For  In Progress
    class Loading : FileIOResult()

}
