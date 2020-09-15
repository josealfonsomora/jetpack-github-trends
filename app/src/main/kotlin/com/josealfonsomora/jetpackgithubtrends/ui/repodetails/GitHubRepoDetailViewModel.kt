package com.josealfonsomora.jetpackgithubtrends.ui.repodetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josealfonsomora.jetpackgithubtrends.commons.toZonedDateTime
import com.josealfonsomora.jetpackgithubtrends.di.IoDispatcher
import com.josealfonsomora.jetpackgithubtrends.di.SystemZoneId
import com.josealfonsomora.jetpackgithubtrends.domain.usecase.GetGithubRepoUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import timber.log.Timber

val dateShortFormatter: DateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)

class GitHubRepoDetailViewModel @ViewModelInject constructor(
    private val useCase: GetGithubRepoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @SystemZoneId private val zone: ZoneId = ZoneId.systemDefault()
) : ViewModel() {
    val name = MutableLiveData<String>()
    val createdDate = MutableLiveData<String>()

    fun loadData(repoId: Int) {
        viewModelScope.launch(ioDispatcher) {
            when (val result = useCase.execute(repoId)) {
                is GetGithubRepoUseCase.Result.Success -> {
                    val repo = result.data
                    name.postValue(repo.fullName)
                    repo.createdAt?.let {
                        createdDate.postValue(it.toZonedDateTime(zone).format(dateShortFormatter))
                    }
                }
                is GetGithubRepoUseCase.Result.Error -> { // This scenario is very unlikely
                    Timber.e(result.throwable)
                }
            }
        }
    }

}
