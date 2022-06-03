package com.example.paging3.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.data.model.Result
import com.example.paging3.data.service.ApiService

class CharacterPagingSource(private val apiService: ApiService) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1
            val responsePage = apiService.getAllCharacters(currentPage)
            val data = responsePage.body()?.results ?: emptyList()
            val responseData = mutableListOf<Result>()

            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}