package co.getpicks.lifecycleawarepresenter.di

import android.app.Application
import co.getpicks.lifecycleawarepresenter.PokemonApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.DaggerApplication


@Singleton
@Component(modules = arrayOf(
        ActivityBuilderModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        RemoteModule::class,
        PersistenceModule::class,
        PresentationModule::class,
        DomainModule::class
))
interface ApplicationComponent : AndroidInjector<PokemonApplication> {

    fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }

}