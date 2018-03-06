package co.getpicks.lifecycleawarepresenter.data

import co.getpicks.lifecycleawarepresenter.data.source.PokemonRemoteDatasource
import io.reactivex.Single

class PokemonRepository
constructor(private val pokemonRemoteDatasource: PokemonRemoteDatasource) {

    fun loadAllPokemon(): Single<List<PokemonData>> = pokemonRemoteDatasource.loadAllPokemons()

}