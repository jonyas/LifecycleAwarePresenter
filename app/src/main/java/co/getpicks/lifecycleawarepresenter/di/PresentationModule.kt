package co.getpicks.lifecycleawarepresenter.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import co.getpicks.lifecycleawarepresenter.presentation.ViewModelFactory
import co.getpicks.lifecycleawarepresenter.presentation.presenter.FavoritePokemonSubPresenter
import co.getpicks.lifecycleawarepresenter.presentation.presenter.FavoritePokemonSubPresenterImpl
import co.getpicks.lifecycleawarepresenter.presentation.presenter.PokemonListActivityPresenter
import com.github.salomonbrys.kodein.*
import io.reactivex.disposables.CompositeDisposable

private typealias ViewModelEntry = Pair<Class<out ViewModel>, ViewModel>
private typealias ViewModels = Set<ViewModelEntry>

class PresentationModule {
    companion object {
        val presentationModule = Kodein.Module {

            bind<CompositeDisposable>() with
                    provider { CompositeDisposable() }

            bind<FavoritePokemonSubPresenter>() with
                    provider { FavoritePokemonSubPresenterImpl(instance(), instance(), instance()) }

            bind<Map<Class<out ViewModel>, ViewModel>>() with
                    provider { instance<ViewModels>().toMap() }

            bind<ViewModelProvider.Factory>() with
                    factory { viewModelMap: Map<Class<out ViewModel>, ViewModel> -> ViewModelFactory(viewModelMap) }

            bind<ViewModelProvider.Factory>() with
                    provider { factory<Map<Class<out ViewModel>, ViewModel>, ViewModelProvider.Factory>().invoke(instance()) }

            bind() from setBinding<ViewModelEntry>()

            bind<ViewModelEntry>().inSet() with
                    provider { PokemonListActivityPresenter::class.java to PokemonListActivityPresenter(instance(), instance(), instance()) }
        }
    }
}
