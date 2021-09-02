package br.com.marcos2silva.marvel_compose.data.datasource.remote

import br.com.marcos2silva.marvel_compose.data.api.MarvelService
import br.com.marcos2silva.marvel_compose.data.datasource.remote.MarvelRemoteDataSource
import br.com.marcos2silva.marvel_compose.data.response.MarvelResponse

class MarvelRemoteDataSourceImpl(
    private val service: MarvelService
) : MarvelRemoteDataSource {
    override suspend fun character(id: Int): MarvelResponse {
        return service.character(id)
    }
}