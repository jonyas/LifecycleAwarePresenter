package co.getpicks.lifecycleawarepresenter.persistence

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import co.getpicks.lifecycleawarepresenter.data.source.FavoritePokemonLocalDatasource
import javax.inject.Inject

class FavoritePokemonPersistence
@Inject constructor(
        sharedPreferences: SharedPreferences
) : FavoritePokemonLocalDatasource {

    private companion object {
        const val PREF_FAV_POKEMON: String = "favorite_pokemon"
    }

    private val rxSharedPreferences: RxSharedPreferences = RxSharedPreferences.create(sharedPreferences)

    override fun loadFavoritePokemonIds(): Observable<Set<Int>> {
        return rxSharedPreferences.getStringSet(PREF_FAV_POKEMON, emptySet())
                .asObservable()
                .map {
                    it.map { it.toInt() }.toSet()
                }
    }

    override fun setPokemonIdAsFavorite(id: Int): Completable {
        return Completable.fromAction {
            with(rxSharedPreferences.getStringSet(PREF_FAV_POKEMON, mutableSetOf<String>())) {
                set(get().apply { add(id.toString()) })
            }
        }
    }

}