package co.getpicks.lifecycleawarepresenter.data

import co.getpicks.lifecycleawarepresenter.data.source.FavoritePokemonLocalDatasource
import io.reactivex.Completable
import io.reactivex.Observable

class FavoritePokemonRepository
constructor(private val favoritePokemonLocalDatasource: FavoritePokemonLocalDatasource) {

    fun loadFavoritePokemonIds(): Observable<Set<Int>> = favoritePokemonLocalDatasource.loadFavoritePokemonIds()

    fun setPokemonIdAsFavorite(id: Int): Completable = favoritePokemonLocalDatasource.setPokemonIdAsFavorite(id)

    fun removePokemonIdFromFavorites(id: Int): Completable = favoritePokemonLocalDatasource.removePokemonIdFromFavorites(id)

}