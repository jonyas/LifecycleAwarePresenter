package co.getpicks.lifecycleawarepresenter.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import co.getpicks.lifecycleawarepresenter.presenter.FavoritePokemonSubPresenter
import co.getpicks.lifecycleawarepresenter.presenter.FavoritePokemonSubPresenterImpl
import co.getpicks.lifecycleawarepresenter.presenter.PokemonListActivityPresenter
import co.getpicks.lifecycleawarepresenter.presentation.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemonListActivityPresenter::class)
    abstract fun bindAuthenticationViewModel(viewModel: PokemonListActivityPresenter): ViewModel

    @Binds
    abstract fun bindFavoritePokemonSubPresenter(favoritePokemonSubPresenter: FavoritePokemonSubPresenterImpl): FavoritePokemonSubPresenter

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }

}
