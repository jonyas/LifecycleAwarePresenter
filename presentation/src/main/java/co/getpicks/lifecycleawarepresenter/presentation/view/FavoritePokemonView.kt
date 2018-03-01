package co.getpicks.lifecycleawarepresenter.presentation.view

import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseView

interface FavoritePokemonView : BaseView {

    fun onAddingPokemonToFavoritesFailed(pokemonUI: PokemonUI, t: Throwable)

    fun onRemovingPokemonFromFavoritesFailed(pokemonUI: PokemonUI, t: Throwable)

}