package co.getpicks.lifecycleawarepresenter.view

import co.getpicks.lifecycleawarepresenter.domain.Pokemon

interface PokemonListActivityView : ShowErrorView, FavoritePokemonView {
    fun showAllPokemon(pokemons: List<Pokemon>)
}