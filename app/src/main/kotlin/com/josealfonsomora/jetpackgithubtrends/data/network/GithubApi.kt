package com.josealfonsomora.jetpackgithubtrends.data.network

import com.google.gson.annotations.SerializedName
import com.josealfonsomora.jetpackgithubtrends.data.model.GithubRepoDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") language: String = "language:kotlin",
        @Query("sort") sortBy: String = "stars",
        @Query("per_page") reposPerPage: Int = 20,
        @Query("page") page: Int = 1
    ): Response<GetGithubRepositoriesResponse>
}

class GetGithubRepositoriesResponse(
    @SerializedName("total_count") val count: Int?,
    @SerializedName("incomplete_results") val incompleteResults: Boolean?,
    val items: List<GithubRepoDataModel>?
)
