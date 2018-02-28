package co.getpicks.lifecycleawarepresenter.domain

import co.getpicks.lifecycleawarepresenter.data.FavoritePokemonRepository
import co.getpicks.lifecycleawarepresenter.data.PokemonData
import co.getpicks.lifecycleawarepresenter.data.PokemonRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class AddPokemonToFavoritesUseCase
@Inject constructor(
        private val favoritePokemonRepository: FavoritePokemonRepository,
        @IoThread private val subscribeOn: Scheduler,
        @MainThread private val observeOn: Scheduler
) {

    fun addPokemonToFavorites(pokemon: Pokemon): Completable {
        return favoritePokemonRepository.setPokemonIdAsFavorite(pokemon.id)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

}