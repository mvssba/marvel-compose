package br.com.marcos2silva.marvel_compose.presentation

import br.com.marcos2silva.marvel_compose.model.Character

sealed class DetailViewState {
    data class Success(val item: Character) : DetailViewState()
    data class Error(val message: String) : DetailViewState()
}
