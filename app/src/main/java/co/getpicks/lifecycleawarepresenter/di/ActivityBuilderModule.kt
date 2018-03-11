package co.getpicks.lifecycleawarepresenter.di

import co.getpicks.lifecycleawarepresenter.ui.PokemonListPresenterActivity
import co.getpicks.lifecycleawarepresenter.ui.PokemonListViewModelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePokemonListActivity(): PokemonListPresenterActivity

    @ContributesAndroidInjector
    abstract fun contributePokemonListViewModelActivity(): PokemonListViewModelActivity

}
