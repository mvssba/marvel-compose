package br.com.marcos2silva.marvel_compose.data.api

import br.com.marcos2silva.marvel_compose.data.response.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("v1/public/characters")
    suspend fun allCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MarvelResponse

    @GET("v1/public/characters")
    suspend fun charactersByName(
        @Query("name") name: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MarvelResponse
}