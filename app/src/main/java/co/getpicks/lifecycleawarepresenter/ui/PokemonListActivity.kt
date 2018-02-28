package co.getpicks.lifecycleawarepresenter.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import co.getpicks.lifecycleawarepresenter.R
import co.getpicks.lifecycleawarepresenter.base.BasePresenterActivity
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.presenter.PokemonListActivityPresenter
import co.getpicks.lifecycleawarepresenter.presentation.view.PokemonListActivityView

class PokemonListActivity :
        BasePresenterActivity<PokemonListActivityPresenter, PokemonListActivityView>(), PokemonListActivityView {


    override val presenterClass = PokemonListActivityPresenter::class.java

    private lateinit var textView: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.activity_main_text)
    }

    override fun showAllPokemon(pokemons: List<PokemonUI>) {
        Toast.makeText(this, "Pokemons Loaded: ${pokemons.size} size", Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        Toast.makeText(this, "Progress", Toast.LENGTH_LONG).show()
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, "Error: ${t.message}", Toast.LENGTH_LONG).show()
    }

    override fun onPokemonAddedToFavorites(pokemonUI: PokemonUI) {
    }

    override fun onPokemonRemovedFromFavorites(pokemonUI: PokemonUI) {
    }

}
