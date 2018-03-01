package co.getpicks.lifecycleawarepresenter.ui.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
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

    private companion object {
        const val KEY_IS_FAVORITE: String = "is_favorite"
    }

    private val pokemons: MutableList<PokemonUI> = mutableListOf()

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) = holder.bind(pokemons[position])

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val o = payloads.first() as Bundle
            holder.holdingPokemon = pokemons[position]
            holder.holdingPokemon.isFavorite = o.getBoolean(KEY_IS_FAVORITE)
            holder.favoritePokemonImageView.setImageResource(
                    if (holder.holdingPokemon.isFavorite) {
                        R.drawable.ic_favorite_black_24dp
                    } else {
                        R.drawable.ic_favorite_border_black_24dp
                    }
            )
        }
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vh_pokemon, parent, false)
        )
    }

    fun setPokemons(pokemons: List<PokemonUI>) {
        if (this.pokemons.isEmpty()) {
            this.pokemons.addAll(pokemons)
            notifyDataSetChanged()
        } else {
            val diffResult = DiffUtil.calculateDiff(PokemonDiffCallback(pokemons, this.pokemons))
            diffResult.dispatchUpdatesTo(this)
        }
    }

    inner class PokemonDiffCallback(
            private val newPokemons: List<PokemonUI>,
            private val oldPokemons: List<PokemonUI>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldPokemons.size
        override fun getNewListSize(): Int = newPokemons.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPokemons[oldItemPosition].id == newPokemons[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldPokemons[oldItemPosition] == newPokemons[newItemPosition]
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val diffBundle = Bundle()
            if (newPokemons[newItemPosition].isFavorite != oldPokemons[oldItemPosition].isFavorite) {
                diffBundle.putBoolean(KEY_IS_FAVORITE, newPokemons[newItemPosition].isFavorite)
            }
            return if (!diffBundle.isEmpty) diffBundle else null
        }
    }

    inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var holdingPokemon: PokemonUI

        val pokemonImageView: ImageView by bindView(R.id.vh_pokemon_image)
        val pokemonNameTextView: TextView by bindView(R.id.vh_pokemon_name)
        val favoritePokemonImageView: ImageView by bindView(R.id.vh_pokemon_favorite)

        init {
            favoritePokemonImageView.setOnClickListener {
                if (!holdingPokemon.isFavorite)
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
