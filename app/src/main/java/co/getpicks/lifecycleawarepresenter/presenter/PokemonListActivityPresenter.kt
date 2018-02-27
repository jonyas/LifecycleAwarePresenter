package co.getpicks.lifecycleawarepresenter.presenter

import co.getpicks.lifecycleawarepresenter.view.PokemonListActivityView
import co.getpicks.lifecycleawarepresenter.presentation.base.BasePresenter
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseSubPresenter
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseView
import io.reactivex.disposables.CompositeDisposable
import co.getpicks.lifecycleawarepresenter.domain.LoadAllPokemonUseCase
import co.getpicks.lifecycleawarepresenter.domain.Pokemon
import javax.inject.Inject

class PokemonListActivityPresenter
@Inject constructor(
        pokemonUseCase: LoadAllPokemonUseCase,
        favPokemonSubPresenter: FavoritePokemonSubPresenter,
        disposables: CompositeDisposable
) : BasePresenter<PokemonListActivityView>(
        disposables,
        @Suppress("UNCHECKED_CAST")
        listOf(favPokemonSubPresenter as BaseSubPresenter<BaseView>)),
        FavoritePokemonSubPresenter by favPokemonSubPresenter {

    private var lastLoadedPokemons: List<Pokemon>? = null

    init {
        disposables.add(
                pokemonUseCase.loadAllPokemon()
                        .subscribe(
                                {
                                    lastLoadedPokemons = it
                                    view?.showAllPokemon(it)
                                },
                                { throwable ->
                                    view?.showError(throwable)
                                })
        )
    }

    override fun onCreate() {
        lastLoadedPokemons?.let { view?.showAllPokemon(it) }
    }
}