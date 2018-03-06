package co.getpicks.lifecycleawarepresenter.di

import co.getpicks.lifecycleawarepresenter.remote.PokemonApi
import co.getpicks.lifecycleawarepresenter.remote.PokemonRestApi
import co.getpicks.lifecycleawarepresenter.remote.PokemonRestApiBuilder
import com.github.salomonbrys.kodein.*

class RemoteModule {
    companion object {
        val remoteModule = Kodein.Module {
            constant("pokemonBaseUrl") with "http://pokeapi.co/api/v2/"

            bind<PokemonRestApi>() with
                    provider { PokemonRestApiBuilder.build(instance("pokemonBaseUrl")) }

            bind<PokemonApi>() with
                    provider { PokemonApi(instance()) }
        }
    }
}
