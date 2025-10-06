package com.example.cryptotracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinViewModel(private val repository: CoinRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<CoinUiState>(CoinUiState.Loading)
    val uiState: StateFlow<CoinUiState> = _uiState

    init {
        refresh()
    }

    fun refresh() {
        _uiState.value = CoinUiState.Loading
        viewModelScope.launch {
            val res = repository.fetchTopCoins()
            if (res.isSuccess) {
                _uiState.value = CoinUiState.Success(res.getOrNull() ?: emptyList())
            } else {
                _uiState.value = CoinUiState.Error(res.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}

sealed class CoinUiState {
    object Loading : CoinUiState()
    data class Success(val coins: List<CoinMarket>) : CoinUiState()
    data class Error(val message: String) : CoinUiState()
}
