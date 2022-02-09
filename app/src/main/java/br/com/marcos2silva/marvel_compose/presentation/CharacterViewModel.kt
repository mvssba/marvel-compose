package br.com.marcos2silva.marvel_compose.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import br.com.marcos2silva.marvel_compose.data.repository.MarvelRepository
import br.com.marcos2silva.marvel_compose.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class CharacterViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _uiStateLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val uiStateLoading: StateFlow<Boolean> = _uiStateLoading

    fun getCharacters(name: String): Flow<PagingData<Character>> {
        return repository.allCharacters(name).distinctUntilChanged()
            .onStart { _uiStateLoading.value = true }
            .onCompletion { _uiStateLoading.value = false }
    }
}