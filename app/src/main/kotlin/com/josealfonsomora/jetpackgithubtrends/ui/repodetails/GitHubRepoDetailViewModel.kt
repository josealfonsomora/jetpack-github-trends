package com.josealfonsomora.jetpackgithubtrends.ui.repodetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josealfonsomora.jetpackgithubtrends.commons.toReadableK
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

val dateShortFormatter: DateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

class GitHubRepoDetailViewModel @ViewModelInject constructor(
    private val useCase: GetGithubRepoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @SystemZoneId private val zone: ZoneId = ZoneId.systemDefault()
) : ViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _createdDate = MutableLiveData<String>()
    val createdDate: LiveData<String> = _createdDate

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _license = MutableLiveData<String>()
    val license: LiveData<String> = _license

    private val _watchers = MutableLiveData<String>()
    val watchers: LiveData<String> = _watchers

    private val _forks = MutableLiveData<String>()
    val forks: LiveData<String> = _forks

    private val _stars = MutableLiveData<String>()
    val stars: LiveData<String> = _stars

    private val _ownerImage = MutableLiveData<String>()
    val ownerImage: LiveData<String> = _ownerImage

    fun loadData(repoId: Int) {
        viewModelScope.launch(ioDispatcher) {
            when (val result = useCase.execute(repoId)) {
                is GetGithubRepoUseCase.Result.Success -> {
                    val repo = result.data
                    _name.postValue(repo.fullName)
                    _title.postValue(repo.name)
                    _url.postValue(repo.htmlUrl)
                    _description.postValue(repo.description)
                    _license.postValue(repo.license?.name)
                    _watchers.postValue(repo.watchersCount.toReadableK())
                    _forks.postValue(repo.forksCount.toReadableK())
                    _stars.postValue(repo.stargazersCount.toReadableK())
                    repo.createdAt?.let {
                        _createdDate.postValue(it.toZonedDateTime(zone).format(dateShortFormatter))
                    }
                    _ownerImage.postValue(repo.owner?.avatarUrl)
                }
                is GetGithubRepoUseCase.Result.Error -> { // This scenario is very unlikely
                    Timber.e(result.throwable)
                }
            }
        }
    }

}
