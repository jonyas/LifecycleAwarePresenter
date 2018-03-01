package co.getpicks.lifecycleawarepresenter.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.getpicks.lifecycleawarepresenter.R
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.utils.bindView
import co.getpicks.lifecycleawarepresenter.utils.load

typealias OnPokemonAddedToFavorites = (PokemonUI) -> Unit
typealias OnPokemonRemovedFromFavorites = (PokemonUI) -> Unit

class PokemonListAdapter
constructor(
        private val onPokemonAddedToFavorites: OnPokemonAddedToFavorites,
        private val onPokemonRemovedFromFavorites: OnPokemonRemovedFromFavorites
) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    private val pokemons: MutableList<PokemonUI> = mutableListOf()

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) = holder.bind(pokemons[position])

    override fun getItemCount(): Int = pokemons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vh_pokemon, parent, false)
        )
    }

    fun setPokemons(pokemons: List<PokemonUI>) {
        this.pokemons.clear()
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }

    fun notifyItemChanged(changedPokemonUI: PokemonUI) {
        notifyItemChanged(pokemons.indexOf(changedPokemonUI))
    }

    inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var holdingPokemon: PokemonUI

        val pokemonImageView: ImageView by bindView(R.id.vh_pokemon_image)
        val pokemonNameTextView: TextView by bindView(R.id.vh_pokemon_name)
        val favoritePokemonImageView: ImageView by bindView(R.id.vh_pokemon_favorite)

        init {
            favoritePokemonImageView.setOnClickListener {
                holdingPokemon.isFavorite = !holdingPokemon.isFavorite
                if (holdingPokemon.isFavorite)
                    onPokemonAddedToFavorites(holdingPokemon)
                else
                    onPokemonRemovedFromFavorites(holdingPokemon)
            }
        }

    }

    private fun PokemonViewHolder.bind(pokemonUI: PokemonUI) {
        holdingPokemon = pokemonUI
        pokemonImageView.load(pokemonUI.image)
        pokemonNameTextView.text = pokemonUI.name
        favoritePokemonImageView.setImageResource(
                if (pokemonUI.isFavorite) {
                    R.drawable.ic_favorite_black_24dp
                } else {
                    R.drawable.ic_favorite_border_black_24dp
                }
        )
    }
}
