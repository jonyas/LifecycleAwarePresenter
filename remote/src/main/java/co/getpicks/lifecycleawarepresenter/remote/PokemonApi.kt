package co.getpicks.lifecycleawarepresenter.remote

import co.getpicks.lifecycleawarepresenter.data.PokemonData
import io.reactivex.Single
import co.getpicks.lifecycleawarepresenter.data.source.PokemonRemoteDatasource
import javax.inject.Inject

class PokemonApi
@Inject constructor(
        private val restApi: PokemonRestApi
) : PokemonRemoteDatasource {

    override fun loadAllPokemons(): Single<List<PokemonData>> {
        return restApi.loadAllPokemons()
                .flattenAsObservable({ it.items.take(5) })
                .map {
                    return@map it.url.split("/").let {
                        it[it.size - 2]
                    }.toInt()
                }.flatMap(
                        { restApi.loadPokemon(it).toObservable() },
                        { id: Int, pokeDetails: PokemonDetailsRemote -> id to pokeDetails }
                ).map { PokemonData(it.first, it.second.name, it.second.images.thumbnail) }
                .toList()
    }

}