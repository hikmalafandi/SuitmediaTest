package com.submission.suitmediatest.ui.thirdscreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.suitmediatest.data.Users
import com.submission.suitmediatest.data.UsersRepository

class ThirdScreenViewModel(usersRepository: UsersRepository) : ViewModel() {

    val users: LiveData<PagingData<Users>> = usersRepository.getUsers().cachedIn(viewModelScope)

}

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThirdScreenViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}