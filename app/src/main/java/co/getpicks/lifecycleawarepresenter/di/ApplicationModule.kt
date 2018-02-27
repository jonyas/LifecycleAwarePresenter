package co.getpicks.lifecycleawarepresenter.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSharePreferences(context: Context): SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
    }

}