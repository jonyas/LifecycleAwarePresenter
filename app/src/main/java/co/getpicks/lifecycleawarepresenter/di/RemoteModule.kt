package co.getpicks.lifecycleawarepresenter.di

import co.getpicks.lifecycleawarepresenter.data.source.PokemonRemoteDatasource
import co.getpicks.lifecycleawarepresenter.remote.PokemonApi
import co.getpicks.lifecycleawarepresenter.remote.PokemonRestApi
import co.getpicks.lifecycleawarepresenter.remote.PokemonRestApiBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindPokemonRemoteDatasource(pokemonApi: PokemonApi): PokemonRemoteDatasource

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providePokemonRestApi(): PokemonRestApi = PokemonRestApiBuilder.build("http://pokeapi.co/api/v2/")
    }

}