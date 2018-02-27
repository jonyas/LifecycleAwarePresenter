package co.getpicks.lifecycleawarepresenter.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import co.getpicks.lifecycleawarepresenter.domain.ComputationThread
import co.getpicks.lifecycleawarepresenter.domain.IoThread
import co.getpicks.lifecycleawarepresenter.domain.MainThread

@Module
class DomainModule {

    @Provides
    @IoThread
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @ComputationThread
    fun provideComputationScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @MainThread
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

}
