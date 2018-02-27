package co.getpicks.lifecycleawarepresenter.di

import dagger.Binds
import dagger.Module
import co.getpicks.lifecycleawarepresenter.data.source.FavoritePokemonLocalDatasource
import co.getpicks.lifecycleawarepresenter.persistence.FavoritePokemonPersistence

@Module
abstract class PersistenceModule {

    @Binds
    abstract fun bindFavoritePokemonDatasource(impl: FavoritePokemonPersistence): FavoritePokemonLocalDatasource

}