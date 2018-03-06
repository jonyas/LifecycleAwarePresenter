package co.getpicks.lifecycleawarepresenter.presentation.presenter

import co.getpicks.lifecycleawarepresenter.domain.AddPokemonToFavoritesUseCase
import co.getpicks.lifecycleawarepresenter.domain.Pokemon
import co.getpicks.lifecycleawarepresenter.domain.RemovePokemonFromFavoritesUseCase
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseSubPresenter
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseSubPresenterImpl
import co.getpicks.lifecycleawarepresenter.presentation.view.FavoritePokemonView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface FavoritePokemonSubPresenter : BaseSubPresenter<FavoritePokemonView> {
    fun markPokemonAsFavorite(pokemonUI: PokemonUI)
    fun removePokemonFromFavorites(pokemonUI: PokemonUI)
}

class FavoritePokemonSubPresenterImpl
constructor(
        disposables: CompositeDisposable,
        private val addPokemonToFavoritesUseCase: AddPokemonToFavoritesUseCase,
        private val removePokemonFromFavoritesUseCase: RemovePokemonFromFavoritesUseCase
) : BaseSubPresenterImpl<FavoritePokemonView>(disposables), FavoritePokemonSubPresenter {

    override fun markPokemonAsFavorite(pokemonUI: PokemonUI) {
        disposables.add(
                Single.just(pokemonUI)
                        .map { Pokemon(it.id, it.name, it.image, true) }
                        .flatMapCompletable { pokemon -> addPokemonToFavoritesUseCase.addPokemonToFavorites(pokemon) }
                        .subscribe(
                                {
                                    // nothing to do. UI should update by itself when
                                    // data stream detects that value has changed
                                },
                                { t: Throwable ->
                                    pokemonUI.isFavorite = false
                                    subPresenterView?.onAddingPokemonToFavoritesFailed(pokemonUI, t)
                                }
                        )
        )
    }

    override fun removePokemonFromFavorites(pokemonUI: PokemonUI) {
        disposables.add(
                Single.just(pokemonUI)
                        .map { Pokemon(it.id, it.name, it.image, false) }
                        .flatMapCompletable { pokemon -> removePokemonFromFavoritesUseCase.removePokemonFromFavorites(pokemon) }
                        .subscribe(
                                {
                                    // nothing to do. UI should update by itself when
                                    // data stream detects that value has changed
                                },
                                { t: Throwable ->
                                    pokemonUI.isFavorite = true
                                    subPresenterView?.onRemovingPokemonFromFavoritesFailed(pokemonUI, t)
                                }
                        )
        )
    }
}

