package com.josealfonsomora.jetpackgithubtrends.data.repository

import androidx.paging.PagingSource
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo
import com.josealfonsomora.jetpackgithubtrends.domain.usecase.GetGithubReposPaginatedUseCase
import retrofit2.HttpException
import java.io.IOException

class GithubPagingSource(
    private val useCase: GetGithubReposPaginatedUseCase
) : PagingSource<Int, GithubRepo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> = try {
        val pageNumber = params.key ?: 0
        when (val result = useCase.execute(page = pageNumber, pageSize = 20)) {
            is GetGithubReposPaginatedUseCase.Result.Success -> {
                val prevKey = if (pageNumber > 0) pageNumber - 1 else null
                val repos = result.data
                val nextKey = if (repos.isNotEmpty()) pageNumber + 1 else null
                LoadResult.Page(
                    data = repos,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            is GetGithubReposPaginatedUseCase.Result.Error -> {
                LoadResult.Error(
                    Throwable(
                        "Error loading paginated github repositories; pageNumber:$pageNumber",
                        result.throwable
                    )
                )
            }
        }
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException) {
        LoadResult.Error(e)
    }
}