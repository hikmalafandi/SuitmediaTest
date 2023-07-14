package com.submission.suitmediatest.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

class UsersRepository(private val apiService: ApiService) {

    fun getUsers(): LiveData<PagingData<Users>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService)
            }
        ).liveData
    }
}