package com.rigelramadhan.beritacompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rigelramadhan.beritacompose.core.data.Resource
import com.rigelramadhan.beritacompose.core.domain.model.News
import com.rigelramadhan.beritacompose.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<Resource<News>> =
        MutableStateFlow(Resource.Loading())
    val uiState: StateFlow<Resource<News>>
        get() = _uiState

    fun init(id: Int) {
        getNewsById(id)
    }

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            newsUseCase.getNewsById(id)
                .catch {
                    _uiState.value = Resource.Error(it.message.toString())
                }
                .collect { news ->
                    _uiState.value = news
                }
        }
    }

    fun setFavorite(news: News, isFavorite: Boolean) {
        newsUseCase.setFavoriteNews(news, isFavorite)
    }
}