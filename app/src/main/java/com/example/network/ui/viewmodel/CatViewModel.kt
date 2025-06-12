package com.example.network.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.data.repository.CatRepository
import com.example.network.ui.state.CatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatUiState())
    val uiState: StateFlow<CatUiState> = _uiState.asStateFlow()

    private var currentPage = 0
    private var isLoadingMore = false

    init {
        loadRandomCats()
    }

    fun loadRandomCats() {
        currentPage = 0
        fetchCats(reset = true)
    }

    fun loadMoreCats() {
        if (isLoadingMore) return
        currentPage++
        fetchCats(reset = false)
    }

    private fun fetchCats(reset: Boolean) {
        viewModelScope.launch {
            if (reset) {
                _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            } else {
                isLoadingMore = true
            }

            repository.getRandomCats(limit = 10, page = currentPage).fold(
                onSuccess = { cats ->
                    val updatedList = if (reset) cats else _uiState.value.cats + cats
                    _uiState.value = _uiState.value.copy(
                        cats = updatedList,
                        isLoading = false,
                        errorMessage = null
                    )
                },
                onFailure = { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Unknown error occurred"
                    )
                }
            )

            isLoadingMore = false
        }
    }
}
