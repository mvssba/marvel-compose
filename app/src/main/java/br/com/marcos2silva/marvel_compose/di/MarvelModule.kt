package br.com.marcos2silva.marvel_compose.di

import androidx.paging.PagingSource
import br.com.marcos2silva.marvel_compose.data.api.MarvelService
import br.com.marcos2silva.marvel_compose.data.datasource.remote.CharactersPagingSource
import br.com.marcos2silva.marvel_compose.data.repository.MarvelRepository
import br.com.marcos2silva.marvel_compose.data.repository.MarvelRepositoryImpl
import br.com.marcos2silva.marvel_compose.model.Character
import br.com.marcos2silva.marvel_compose.presentation.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

object MarvelModule {

    private val presentation = module {
        viewModel { CharacterViewModel(repository = get()) }
    }

    private val domain = module {
        factory<MarvelRepository> { MarvelRepositoryImpl(service = get()) }
    }

    private val data = module {
        factory<PagingSource<Int, Character>> {
            CharactersPagingSource(service = get(), "")
        }
    }

    private val service = module {
        factory { providerMarvelService(get()) }
    }

    private fun providerMarvelService(retrofit: Retrofit) =
        retrofit.create(MarvelService::class.java)

    fun load() {
        loadKoinModules(
            listOf(
                presentation,
                domain,
                data,
                service
            )
        )
    }
}