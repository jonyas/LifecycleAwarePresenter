package co.getpicks.lifecycleawarepresenter.presentation.view

import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseView

interface FavoritePokemonView : ShowErrorView, BaseView {

    fun onPokemonAddedToFavorites(pokemonUI: PokemonUI)

    fun onPokemonRemovedFromFavorites(pokemonUI: PokemonUI)

}