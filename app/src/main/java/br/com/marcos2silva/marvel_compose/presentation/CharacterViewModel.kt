package br.com.marcos2silva.marvel_compose.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import br.com.marcos2silva.marvel_compose.data.repository.MarvelRepository
import br.com.marcos2silva.marvel_compose.data.api.MarvelService
import br.com.marcos2silva.marvel_compose.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _uiState: MutableLiveData<DetailViewState> = MutableLiveData()
    val uiState: LiveData<DetailViewState> = _uiState

    fun getCharacters(name: String): Flow<PagingData<Character>> {
        return repository.allCharacters(name)
    }

    fun character(id: Int) {
        viewModelScope.launch {
            val character = repository.character(id)

            character?.let {
                _uiState.value = DetailViewState.Success(it)
            } ?: run {
                _uiState.value = DetailViewState.Error("")
            }
        }
    }
}