package co.getpicks.lifecycleawarepresenter.domain

import co.getpicks.lifecycleawarepresenter.data.FavoritePokemonRepository
import co.getpicks.lifecycleawarepresenter.data.PokemonData
import co.getpicks.lifecycleawarepresenter.data.PokemonRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction

class LoadAllPokemonUseCase
constructor(
        private val pokemonRepository: PokemonRepository,
        private val favoritePokemonRepository: FavoritePokemonRepository,
        private val subscribeOn: Scheduler,
        private val observeOn: Scheduler
) {

    fun loadAllPokemon(): Observable<List<Pokemon>> {
        return Observable.combineLatest(
                pokemonRepository.loadAllPokemon().toObservable(),
                favoritePokemonRepository.loadFavoritePokemonIds(),
                BiFunction<List<PokemonData>, Set<Int>, List<Pokemon>>
                { allPokemon: List<PokemonData>, favPokemonIds: Set<Int> ->
                    allPokemon.map { Pokemon(it.id, it.name, it.image, favPokemonIds.contains(it.id)) }
                }
        ).subscribeOn(subscribeOn).observeOn(observeOn)
    }

}