package co.getpicks.lifecycleawarepresenter.presentation.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel
constructor(
        protected val disposables: CompositeDisposable
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}