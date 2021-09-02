package br.com.marcos2silva.marvel_compose.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.marcos2silva.marvel_compose.ErrorHandle
import br.com.marcos2silva.marvel_compose.data.api.MarvelService
import br.com.marcos2silva.marvel_compose.model.Character
import com.google.gson.GsonBuilder
import retrofit2.HttpException

private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 0

class CharactersPagingSource(
    private val service: MarvelService,
    private val name: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) {
            (position * NETWORK_PAGE_SIZE)
        } else INITIAL_LOAD_SIZE

        return try {
            val response =
                if (name.isEmpty())
                    service.allCharacters(offset = offset, limit = NETWORK_PAGE_SIZE)
                else
                    service.charactersByName(
                        name = name,
                        offset = offset,
                        limit = NETWORK_PAGE_SIZE
                    )

            LoadResult.Page(
                data = response.data.results.map { characterResponse ->
                    Character(
                        id = characterResponse.id,
                        name = characterResponse.name,
                        thumbnail = "${characterResponse.thumbnail.path}.${characterResponse.thumbnail.extension}",
                        isFavorite = false
                    )
                },
                prevKey = null,
                nextKey = if (response.data.results.isEmpty()) null else position + 1
            )
        } catch (exception: HttpException) {
            LoadResult.Error(handleError(exception))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun handleError(exception: HttpException): Throwable {
        val body = exception.response()?.errorBody()
        val error = GsonBuilder().create().fromJson(body?.string(), ErrorHandle::class.java)

        return Throwable(message = error.message)
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = null
}