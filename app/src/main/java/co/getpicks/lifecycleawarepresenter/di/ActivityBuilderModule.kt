package co.getpicks.lifecycleawarepresenter.di

import co.getpicks.lifecycleawarepresenter.ui.PokemonListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePokemonListActivity(): PokemonListActivity

}
