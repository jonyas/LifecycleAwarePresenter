package co.getpicks.lifecycleawarepresenter.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.Toast
import co.getpicks.lifecycleawarepresenter.R
import co.getpicks.lifecycleawarepresenter.base.BaseViewModelActivity
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.viewmodel.FavoritePokemonState
import co.getpicks.lifecycleawarepresenter.presentation.viewmodel.FavoritePokemonViewModel
import co.getpicks.lifecycleawarepresenter.presentation.viewmodel.PokemonListState
import co.getpicks.lifecycleawarepresenter.presentation.viewmodel.PokemonListViewModel
import co.getpicks.lifecycleawarepresenter.ui.adapter.PokemonListAdapter
import co.getpicks.lifecycleawarepresenter.utils.bindView
import co.getpicks.lifecycleawarepresenter.utils.go
import co.getpicks.lifecycleawarepresenter.utils.show

@SuppressLint("Registered")
class PokemonListViewModelActivity : BaseViewModelActivity() {

    private val pokemonListViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getViewModel<PokemonListViewModel>()
    }
    private val favoritePokemonViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getViewModel<FavoritePokemonViewModel>()
    }

    private val progressBar: ProgressBar by bindView(R.id.activity_pokemonlist_progressbar)
    private val recyclerView: RecyclerView by bindView(R.id.activity_pokemonlist_recyclerview)
    private val pokemonAdapter: PokemonListAdapter = PokemonListAdapter(
            { favoritePokemonViewModel.markPokemonAsFavorite(it) },
            { favoritePokemonViewModel.removePokemonFromFavorites(it) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemonlist)
        with(recyclerView) {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(this@PokemonListViewModelActivity)
        }
        pokemonListViewModel.pokemonListState.observe(this, pokemonListStateObserver)
        favoritePokemonViewModel.pokemonListState.observe(this, favoritePokemonStateObserver)
    }

    private fun showAllPokemon(pokemons: List<PokemonUI>) = pokemonAdapter.setPokemons(pokemons)

    private fun hideProgress() = progressBar.go()

    private fun showProgress() = progressBar.show()

    private fun showError(t: Throwable) {
        Toast.makeText(this, "Error: ${t.message}", Toast.LENGTH_LONG).show()
    }

    private fun onPokemonAddedToFavorites() {
        pokemonAdapter.notifyDataSetChanged()
    }

    private fun onPokemonRemovedFromFavorites() {
        pokemonAdapter.notifyDataSetChanged()
    }

    private fun onAddingPokemonToFavoritesFailed(t: Throwable) {
        Toast.makeText(this, "Error adding to favs: ${t.message}", Toast.LENGTH_LONG).show()
        pokemonAdapter.notifyDataSetChanged()
    }

    private fun onRemovingPokemonFromFavoritesFailed(t: Throwable) {
        Toast.makeText(this, "Error adding from favs: ${t.message}", Toast.LENGTH_LONG).show()
        pokemonAdapter.notifyDataSetChanged()
    }

    private val pokemonListStateObserver = Observer<PokemonListState> { state ->
        when (state) {
            PokemonListState.InProgress -> showProgress()
            is PokemonListState.Idle -> {
                hideProgress()
                state.loadedData?.let { showAllPokemon(it) }
            }
            is PokemonListState.Failure -> {
                hideProgress()
                showError(state.throwable)
            }
        }
    }

    private val favoritePokemonStateObserver = Observer<FavoritePokemonState> { state ->
        when (state) {
            is FavoritePokemonState.AddedToFavoriteSuccess -> onPokemonAddedToFavorites()
            is FavoritePokemonState.RemoveFromFavoriteSuccess -> onPokemonRemovedFromFavorites()
            is FavoritePokemonState.AddedToFavoriteFailure -> onAddingPokemonToFavoritesFailed(state.throwable)
            is FavoritePokemonState.RemoveFromFavoriteFailure -> onRemovingPokemonFromFavoritesFailed(state.throwable)
        }
    }
}
