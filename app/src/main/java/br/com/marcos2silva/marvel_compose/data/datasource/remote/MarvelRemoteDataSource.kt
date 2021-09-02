package br.com.marcos2silva.marvel_compose.data.datasource.remote

import br.com.marcos2silva.marvel_compose.data.response.MarvelResponse

interface MarvelRemoteDataSource {
    suspend fun character(id: Int): MarvelResponse
}