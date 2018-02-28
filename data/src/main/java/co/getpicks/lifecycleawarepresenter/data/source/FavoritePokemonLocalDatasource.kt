package co.getpicks.lifecycleawarepresenter.data.source

import io.reactivex.Completable
import io.reactivex.Observable

interface FavoritePokemonLocalDatasource {

    fun loadFavoritePokemonIds(): Observable<Set<Int>>

    fun setPokemonIdAsFavorite(id: Int): Completable

    fun removePokemonIdFromFavorites(id: Int): Completable

}