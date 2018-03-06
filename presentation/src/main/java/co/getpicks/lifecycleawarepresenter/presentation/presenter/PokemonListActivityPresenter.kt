package co.getpicks.lifecycleawarepresenter.presentation.presenter

import co.getpicks.lifecycleawarepresenter.domain.LoadAllPokemonUseCase
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.base.*
import co.getpicks.lifecycleawarepresenter.presentation.view.PokemonListActivityView
import io.reactivex.disposables.CompositeDisposable

class PokemonListActivityPresenter
constructor(
        pokemonUseCase: LoadAllPokemonUseCase,
        favPokemonSubPresenter: FavoritePokemonSubPresenter,
        disposables: CompositeDisposable
) : BasePresenter<PokemonListActivityView>(
        disposables,
        @Suppress("UNCHECKED_CAST")
        listOf(favPokemonSubPresenter as BaseSubPresenter<BaseView>)
), FavoritePokemonSubPresenter by favPokemonSubPresenter {

    private var loadPokemonState = PresenterState(::renderState)

    init {
        disposables.add(
                pokemonUseCase.loadAllPokemon()
                        .doOnSubscribe { loadPokemonState.state = PresenterStateValue.InProgress }
                        .map { it.map { PokemonUI(it.id, it.name, it.image, it.isFavorite) } }
                        .distinctUntilChanged()
                        .subscribe(
                                { loadPokemonState.state = PresenterStateValue.Idle(it) },
                                { loadPokemonState.state = PresenterStateValue.Failure(it) }
                        )
        )
    }

    override fun onCreate() {
        loadPokemonState.state?.let { renderState(it) }
    }

    private fun renderState(state: BasePresenterStateValue?) {
        when (state) {
            is PresenterStateValue.Failure -> {
                view?.hideProgress()
                view?.showError(state.throwable)
            }
            PresenterStateValue.InProgress -> view?.showProgress()
            is PresenterStateValue.Idle -> {
                view?.hideProgress()
                state.loadedData?.let { view?.showAllPokemon(it) }
            }
        }
    }

    private sealed class PresenterStateValue : BasePresenterStateValue {
        object InProgress : PresenterStateValue()
        data class Idle(val loadedData: List<PokemonUI>? = null) : PresenterStateValue()
        data class Failure(val throwable: Throwable) : PresenterStateValue()
    }
}