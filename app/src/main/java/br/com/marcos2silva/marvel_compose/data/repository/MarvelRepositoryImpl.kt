package br.com.marcos2silva.marvel_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.marcos2silva.marvel_compose.data.api.MarvelService
import br.com.marcos2silva.marvel_compose.data.datasource.remote.CharactersPagingSource
import br.com.marcos2silva.marvel_compose.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MarvelRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val service: MarvelService
) : MarvelRepository {

    override fun allCharacters(name: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CharactersPagingSource(service, name) }
        ).flow
            .flowOn(ioDispatcher)
    }
}