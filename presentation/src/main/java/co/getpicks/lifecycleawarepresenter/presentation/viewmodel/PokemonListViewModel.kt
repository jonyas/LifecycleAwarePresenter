package co.getpicks.lifecycleawarepresenter.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import co.getpicks.lifecycleawarepresenter.domain.LoadAllPokemonUseCase
import co.getpicks.lifecycleawarepresenter.presentation.PokemonUI
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonListViewModel
@Inject constructor(
        pokemonUseCase: LoadAllPokemonUseCase,
        disposables: CompositeDisposable
) : BaseViewModel(disposables) {

    val pokemonListState = MutableLiveData<PokemonListState>()

    init {
        disposables.add(
                pokemonUseCase.loadAllPokemon()
                        .doOnSubscribe { pokemonListState.value = PokemonListState.InProgress }
                        .map { it.map { PokemonUI(it.id, it.name, it.image, it.isFavorite) } }
                        .distinctUntilChanged()
                        .subscribe(
                                { pokemonListState.value = PokemonListState.Idle(it) },
                                { pokemonListState.value = PokemonListState.Failure(it) }
                        )
        )
    }

}

sealed class PokemonListState {
    object InProgress : PokemonListState()
    data class Idle(val loadedData: List<PokemonUI>? = null) : PokemonListState()
    data class Failure(val throwable: Throwable) : PokemonListState()
}
