package co.getpicks.lifecycleawarepresenter.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.provider

class PersistenceModule {
    companion object {
        fun persistenceModule(ctx: Context) = Kodein.Module {

            bind<SharedPreferences>() with
                    factory { ctx: Context -> PreferenceManager.getDefaultSharedPreferences(ctx) }

            bind<SharedPreferences>() with
                    provider { factory<Context, SharedPreferences>().invoke(ctx) }
        }
    }
}
