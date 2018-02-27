package co.getpicks.lifecycleawarepresenter.data.source

import co.getpicks.lifecycleawarepresenter.data.PokemonData
import io.reactivex.Single

interface PokemonRemoteDatasource {

    fun loadAllPokemons(): Single<List<PokemonData>>

}