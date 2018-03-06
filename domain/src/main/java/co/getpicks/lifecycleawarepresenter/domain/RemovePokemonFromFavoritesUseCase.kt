package co.getpicks.lifecycleawarepresenter.domain

import co.getpicks.lifecycleawarepresenter.data.FavoritePokemonRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class RemovePokemonFromFavoritesUseCase
constructor(
        private val favoritePokemonRepository: FavoritePokemonRepository,
        private val subscribeOn: Scheduler,
        private val observeOn: Scheduler
) {

    fun removePokemonFromFavorites(pokemon: Pokemon): Completable {
        return favoritePokemonRepository.removePokemonIdFromFavorites(pokemon.id)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

}