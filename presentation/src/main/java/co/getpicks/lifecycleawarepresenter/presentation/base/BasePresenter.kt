package co.getpicks.lifecycleawarepresenter.presentation.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseView>
constructor(
        protected val disposables: CompositeDisposable,
        private val subPresenters: List<BaseSubPresenter<in BaseView>>? = null
) : ViewModel(), LifecycleObserver {

    var view: V? = null
        set(viewToAssign) {
            field = viewToAssign
            subPresenters?.forEach {
                it.subPresenterView = viewToAssign
            }
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {
        Log.d("BasePresenter", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() {
        Log.d("BasePresenter", "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onResume() {
        Log.d("BasePresenter", "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected open fun onPause() {
        Log.d("BasePresenter", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun onStop() {
        Log.d("BasePresenter", "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestroy() {
        Log.d("BasePresenter", "onDestroy")
        view = null
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        subPresenters?.forEach { it.clear() }
    }

}
