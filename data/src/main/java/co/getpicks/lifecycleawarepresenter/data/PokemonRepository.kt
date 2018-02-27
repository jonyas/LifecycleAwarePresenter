package co.getpicks.lifecycleawarepresenter.data

import io.reactivex.Single
import co.getpicks.lifecycleawarepresenter.data.source.PokemonRemoteDatasource
import javax.inject.Inject

class PokemonRepository
@Inject constructor(private val pokemonRemoteDatasource: PokemonRemoteDatasource) {

    fun loadAllPokemon(): Single<List<PokemonData>> = pokemonRemoteDatasource.loadAllPokemons()

}