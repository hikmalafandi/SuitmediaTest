package com.submission.suitmediatest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersPagingSource(private val apiService: ApiService) : PagingSource<Int, Users>() {

    override fun getRefreshKey(state: PagingState<Int, Users>): Int? {
        return state.anchorPosition?.let { anchorposition ->
            val anchorPage = state.closestPageToPosition(anchorposition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users> {
        return try {

            val page = params.key ?: 1
            val size = params.loadSize

            val apiService = ApiConfig.getApiService()

            val response = withContext(Dispatchers.IO) {
                apiService.getUsers(page, size).execute()
            }

            if (response.isSuccessful) {
                val usersResponse = response.body()
                val users = usersResponse?.data ?: emptyList()

                LoadResult.Page(
                    data = users,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (users.isNullOrEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(IllegalStateException("Gagal memuat user. Status code: ${response.code()}"))
            }

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}