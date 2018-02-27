package co.getpicks.lifecycleawarepresenter.domain

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention
annotation class IoThread

@Qualifier
@MustBeDocumented
@Retention
annotation class MainThread

@Qualifier
@MustBeDocumented
@Retention
annotation class ComputationThread