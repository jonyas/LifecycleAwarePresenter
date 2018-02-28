package co.getpicks.lifecycleawarepresenter.presentation.view

import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI

interface PokemonListActivityView : ShowErrorView, FavoritePokemonView {
    fun showAllPokemon(pokemons: List<PokemonUI>)
    fun showProgress()
}