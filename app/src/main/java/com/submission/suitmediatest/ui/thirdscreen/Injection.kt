package com.submission.suitmediatest.ui.thirdscreen

import android.content.Context
import com.submission.suitmediatest.data.ApiConfig
import com.submission.suitmediatest.data.UsersRepository

object Injection {
    fun provideRepository(context: Context): UsersRepository {
        val apiService = ApiConfig.getApiService()
        return UsersRepository(apiService)
    }
}