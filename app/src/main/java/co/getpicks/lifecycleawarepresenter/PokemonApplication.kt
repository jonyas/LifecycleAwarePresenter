package co.getpicks.lifecycleawarepresenter

import android.app.Application
import android.content.Context
import co.getpicks.lifecycleawarepresenter.di.DataModule.Companion.dataModule
import co.getpicks.lifecycleawarepresenter.di.DomainModule.Companion.domainModule
import co.getpicks.lifecycleawarepresenter.di.PersistenceModule.Companion.persistenceModule
import co.getpicks.lifecycleawarepresenter.di.PresentationModule.Companion.presentationModule
import co.getpicks.lifecycleawarepresenter.di.RemoteModule.Companion.remoteModule
import com.github.salomonbrys.kodein.*

class PokemonApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(dataModule)
        import(domainModule)
        import(persistenceModule(this@PokemonApplication))
        import(presentationModule)
        import(remoteModule)
    }

}