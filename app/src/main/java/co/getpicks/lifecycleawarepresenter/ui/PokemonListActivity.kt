package co.getpicks.lifecycleawarepresenter.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.Toast
import co.getpicks.lifecycleawarepresenter.R
import co.getpicks.lifecycleawarepresenter.base.BasePresenterActivity
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.presenter.PokemonListActivityPresenter
import co.getpicks.lifecycleawarepresenter.presentation.view.PokemonListActivityView
import co.getpicks.lifecycleawarepresenter.ui.adapter.PokemonListAdapter
import co.getpicks.lifecycleawarepresenter.utils.bindView
import co.getpicks.lifecycleawarepresenter.utils.go
import co.getpicks.lifecycleawarepresenter.utils.show

class PokemonListActivity :
        BasePresenterActivity<PokemonListActivityPresenter, PokemonListActivityView>(), PokemonListActivityView {

    override val presenterClass = PokemonListActivityPresenter::class.java

    private val progressBar: ProgressBar by bindView(R.id.activity_pokemonlist_progressbar)
    private val recyclerView: RecyclerView by bindView(R.id.activity_pokemonlist_recyclerview)
    private val pokemonAdapter: PokemonListAdapter = PokemonListAdapter(
            { presenter.markPokemonAsFavorite(it) },
            { presenter.removePokemonFromFavorites(it) }
    )

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemonlist)
        with(recyclerView) {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(this@PokemonListActivity)
        }
    }

    override fun showAllPokemon(pokemons: List<PokemonUI>) = pokemonAdapter.setPokemons(pokemons)

    override fun hideProgress() = progressBar.go()

    override fun showProgress() = progressBar.show()

    override fun showError(t: Throwable) {
        Toast.makeText(this, "Error: ${t.message}", Toast.LENGTH_LONG).show()
    }

    override fun onPokemonAddedToFavorites(pokemonUI: PokemonUI) {
        pokemonAdapter.notifyItemChanged(pokemonUI)
    }

    override fun onPokemonRemovedFromFavorites(pokemonUI: PokemonUI) {
        pokemonAdapter.notifyItemChanged(pokemonUI)
    }

}
