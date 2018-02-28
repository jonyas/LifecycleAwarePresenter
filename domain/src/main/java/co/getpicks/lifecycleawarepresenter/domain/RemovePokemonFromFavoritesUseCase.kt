package co.getpicks.lifecycleawarepresenter.domain

import co.getpicks.lifecycleawarepresenter.data.FavoritePokemonRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

class RemovePokemonFromFavoritesUseCase
@Inject constructor(
        private val favoritePokemonRepository: FavoritePokemonRepository,
        @IoThread private val subscribeOn: Scheduler,
        @MainThread private val observeOn: Scheduler
) {

    fun removePokemonFromFavorites(pokemon: Pokemon): Completable {
        return favoritePokemonRepository.removePokemonIdFromFavorites(pokemon.id)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

}