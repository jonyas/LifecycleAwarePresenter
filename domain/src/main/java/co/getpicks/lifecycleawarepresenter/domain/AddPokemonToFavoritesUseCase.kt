package co.getpicks.lifecycleawarepresenter.domain

import co.getpicks.lifecycleawarepresenter.data.FavoritePokemonRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class AddPokemonToFavoritesUseCase
constructor(
        private val favoritePokemonRepository: FavoritePokemonRepository,
        private val subscribeOn: Scheduler,
        private val observeOn: Scheduler
) {

    fun addPokemonToFavorites(pokemon: Pokemon): Completable {
        return favoritePokemonRepository.setPokemonIdAsFavorite(pokemon.id)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

}