package co.getpicks.lifecycleawarepresenter.presenter

import co.getpicks.lifecycleawarepresenter.view.FavoritePokemonView
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseSubPresenter
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseSubPresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

interface FavoritePokemonSubPresenter : BaseSubPresenter<FavoritePokemonView> {
    fun markPokemonAsFavorite()
    fun removePokemonFromFavorites()
}

class FavoritePokemonSubPresenterImpl
@Inject constructor(
        disposables: CompositeDisposable
) : BaseSubPresenterImpl<FavoritePokemonView>(disposables), FavoritePokemonSubPresenter {

    override fun markPokemonAsFavorite() {
        subPresenterView?.onPokemonAddedToFavorites()
    }

    override fun removePokemonFromFavorites() {
        subPresenterView?.onPokemonAddedToFavorites()
    }
}

