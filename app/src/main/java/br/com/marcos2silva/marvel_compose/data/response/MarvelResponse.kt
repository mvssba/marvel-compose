package br.com.marcos2silva.marvel_compose.data.response

data class MarvelResponse(
    val code: Int,
    val etag: String,
    val data: DataResponse
)
