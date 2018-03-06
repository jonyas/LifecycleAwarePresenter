package co.getpicks.lifecycleawarepresenter.di

import co.getpicks.lifecycleawarepresenter.data.FavoritePokemonRepository
import co.getpicks.lifecycleawarepresenter.data.PokemonRepository
import co.getpicks.lifecycleawarepresenter.data.source.FavoritePokemonLocalDatasource
import co.getpicks.lifecycleawarepresenter.data.source.PokemonRemoteDatasource
import co.getpicks.lifecycleawarepresenter.persistence.FavoritePokemonPersistence
import co.getpicks.lifecycleawarepresenter.remote.PokemonApi
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider

class DataModule {
    companion object {
        val dataModule = Kodein.Module {
            bind<FavoritePokemonRepository>() with provider { FavoritePokemonRepository(instance()) }
            bind<FavoritePokemonLocalDatasource>() with provider { FavoritePokemonPersistence(instance()) }
            bind<PokemonRepository>() with provider { PokemonRepository(instance()) }
            bind<PokemonRemoteDatasource>() with provider { PokemonApi(instance()) }
        }
    }
}
