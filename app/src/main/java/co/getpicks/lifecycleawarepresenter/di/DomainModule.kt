package co.getpicks.lifecycleawarepresenter.di

import co.getpicks.lifecycleawarepresenter.domain.AddPokemonToFavoritesUseCase
import co.getpicks.lifecycleawarepresenter.domain.LoadAllPokemonUseCase
import co.getpicks.lifecycleawarepresenter.domain.RemovePokemonFromFavoritesUseCase
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DomainModule {
    companion object {
        val domainModule = Kodein.Module {
            bind<Scheduler>("IoThread") with provider { Schedulers.io() }
            bind<Scheduler>("ComputationThread") with provider { Schedulers.computation() }
            bind<Scheduler>("MainThread") with provider { AndroidSchedulers.mainThread() }

            bind<AddPokemonToFavoritesUseCase>() with
                    provider { AddPokemonToFavoritesUseCase(instance(), instance("IoThread"), instance("MainThread")) }
            bind<LoadAllPokemonUseCase>() with
                    provider { LoadAllPokemonUseCase(instance(), instance(), instance("IoThread"), instance("MainThread")) }
            bind<RemovePokemonFromFavoritesUseCase>() with
                    provider { RemovePokemonFromFavoritesUseCase(instance(), instance("IoThread"), instance("MainThread")) }
        }
    }
}