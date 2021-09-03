package br.com.marcos2silva.marvel_compose.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import br.com.marcos2silva.marvel_compose.data.repository.MarvelRepository
import br.com.marcos2silva.marvel_compose.model.Character
import kotlinx.coroutines.flow.Flow

class CharacterViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    fun getCharacters(name: String): Flow<PagingData<Character>> {
        return repository.allCharacters(name)
    }
}