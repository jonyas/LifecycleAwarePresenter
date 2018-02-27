package co.getpicks.lifecycleawarepresenter.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import co.getpicks.lifecycleawarepresenter.presentation.base.BasePresenter
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BasePresenterActivity<P : BasePresenter<V>, V : BaseView> : DaggerAppCompatActivity() {

    protected abstract val presenterClass: Class<P>
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val presenter: P by lazy(LazyThreadSafetyMode.NONE) {
        @Suppress("UNCHECKED_CAST")
        ViewModelProviders.of(this, viewModelFactory).get(presenterClass)
                .also { it.view = this as V }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(presenter)
        super.onDestroy()
    }

}
