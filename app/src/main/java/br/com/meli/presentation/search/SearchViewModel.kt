package br.com.meli.presentation.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class SearchViewModel : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val isSearchEnabled: Flow<Boolean> = _query.map { it.trim().length >= 2 }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }
}