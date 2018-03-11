package co.getpicks.lifecycleawarepresenter.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import co.getpicks.lifecycleawarepresenter.domain.AddPokemonToFavoritesUseCase
import co.getpicks.lifecycleawarepresenter.domain.Pokemon
import co.getpicks.lifecycleawarepresenter.domain.RemovePokemonFromFavoritesUseCase
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FavoritePokemonViewModel
@Inject constructor(
        disposables: CompositeDisposable,
        private val addPokemonToFavoritesUseCase: AddPokemonToFavoritesUseCase,
        private val removePokemonFromFavoritesUseCase: RemovePokemonFromFavoritesUseCase
) : BaseViewModel(disposables) {

    val pokemonListState = MutableLiveData<FavoritePokemonState>()

    fun markPokemonAsFavorite(pokemonUI: PokemonUI) {
        pokemonUI.isFavorite = true
        disposables.add(
                Single.just(pokemonUI)
                        .map { Pokemon(it.id, it.name, it.image, true) }
                        .flatMapCompletable { pokemon -> addPokemonToFavoritesUseCase.addPokemonToFavorites(pokemon) }
                        .subscribe(
                                { pokemonListState.value = FavoritePokemonState.AddedToFavoriteSuccess(pokemonUI) },
                                { pokemonListState.value = FavoritePokemonState.AddedToFavoriteFailure(pokemonUI, it) }
                        )
        )
    }

    fun removePokemonFromFavorites(pokemonUI: PokemonUI) {
        pokemonUI.isFavorite = false
        disposables.add(
                Single.just(pokemonUI)
                        .map { Pokemon(it.id, it.name, it.image, false) }
                        .flatMapCompletable { pokemon -> removePokemonFromFavoritesUseCase.removePokemonFromFavorites(pokemon) }
                        .subscribe(
                                { pokemonListState.value = FavoritePokemonState.RemoveFromFavoriteSuccess(pokemonUI) },
                                { pokemonListState.value = FavoritePokemonState.RemoveFromFavoriteFailure(pokemonUI, it) }
                        )
        )
    }

}

sealed class FavoritePokemonState {
    data class AddedToFavoriteSuccess(val pokemonUI: PokemonUI) : FavoritePokemonState()
    data class AddedToFavoriteFailure(val pokemonUI: PokemonUI, val throwable: Throwable) : FavoritePokemonState()
    data class RemoveFromFavoriteSuccess(val pokemonUI: PokemonUI) : FavoritePokemonState()
    data class RemoveFromFavoriteFailure(val pokemonUI: PokemonUI, val throwable: Throwable) : FavoritePokemonState()
}
