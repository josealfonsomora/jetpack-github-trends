package com.josealfonsomora.jetpackgithubtrends.ui.reposlist

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josealfonsomora.jetpackgithubtrends.di.IoDispatcher
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepository
import com.josealfonsomora.jetpackgithubtrends.domain.usecase.GetGithubReposUseCase
import com.josealfonsomora.jetpackgithubtrends.ui.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class GitHubReposListViewModel @ViewModelInject constructor(
    val useCase: GetGithubReposUseCase,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _loadingVisibility = MutableLiveData<Int>(View.GONE)
    val loadingVisibility: LiveData<Int> = _loadingVisibility

    private val _githubRepos = MutableLiveData<List<GithubRepository>>()
    val githubRepos: LiveData<List<GithubRepository>> = _githubRepos

    private val _stateEvent = MutableLiveData<Event<State>>()
    val stateEvent: LiveData<Event<State>> = _stateEvent

    fun loadData() {
        _loadingVisibility.value = View.VISIBLE
        viewModelScope.launch(ioDispatcher) {
            val result = useCase.execute()
            _loadingVisibility.postValue(View.GONE)
            when (result) {
                is GetGithubReposUseCase.Result.Success -> _githubRepos.postValue(result.data)
                is GetGithubReposUseCase.Result.Error -> _stateEvent.postValue(Event(State.ErrorLoadingReposEvent))
            }
        }
    }

    sealed class State {
        object ErrorLoadingReposEvent : State()
    }
}