package com.appnews.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appnews.domain.GetHeadlinesUseCase
import com.appnews.domain.model.Article
import com.appnews.domain.model.StateView
import kotlinx.coroutines.launch

class HomeViewModel (private val getHeadlines : GetHeadlinesUseCase) : ViewModel() {

    private val _headlinesState = MutableLiveData<StateView<List<Article>>>()
    val headlinesState : LiveData<StateView<List<Article>>>
        get() = _headlinesState

    fun getArticles(){
        viewModelScope.launch {
            when(val result = getHeadlines.invoke()){
                is StateView.DataLoaded -> _headlinesState.value = result
                is StateView.Error -> _headlinesState.value = result
            }
        }
    }

}