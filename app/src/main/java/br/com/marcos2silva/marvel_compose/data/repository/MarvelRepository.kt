package br.com.marcos2silva.marvel_compose.data.repository

import androidx.paging.PagingData
import br.com.marcos2silva.marvel_compose.model.Character
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun character(id: Int): Character?
    fun allCharacters(name: String): Flow<PagingData<Character>>
}