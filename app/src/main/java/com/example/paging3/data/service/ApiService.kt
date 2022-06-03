package com.example.paging3.data.service

import com.example.paging3.data.model.ResponseApi
import com.example.paging3.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(@Query("page") page: Int): Response<ResponseApi>
}