package com.github.repo.network

import com.github.repo.model.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitService {

    @GET("/search/repositories")
    fun getForums(
            @Query("order") order: String,
            @Query("q") query: String,
            @Query("per_page") perPage: Int,
            @Query("page") page: Int
    ): Call<RepositoryResponse>

}
