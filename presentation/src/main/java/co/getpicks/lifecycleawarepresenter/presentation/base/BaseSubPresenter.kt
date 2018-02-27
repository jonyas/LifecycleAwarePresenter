package co.getpicks.lifecycleawarepresenter.presentation.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseSubPresenterImpl<V : BaseView>
constructor(
        protected val disposables: CompositeDisposable
) : BaseSubPresenter<V> {

    override var subPresenterView: V? = null

    override fun clear() {
        disposables.clear()
    }

}

interface BaseSubPresenter<V : BaseView> {
    var subPresenterView: V?
    fun clear()
}