package co.getpicks.lifecycleawarepresenter.view

import co.getpicks.lifecycleawarepresenter.presentation.base.BaseView

interface FavoritePokemonView : ShowErrorView, BaseView {

    fun onPokemonAddedToFavorites()

    fun onPokemonRemovedFromFavorites()

}