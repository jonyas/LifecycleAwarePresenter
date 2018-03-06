package co.getpicks.lifecycleawarepresenter.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import co.getpicks.lifecycleawarepresenter.presentation.base.BasePresenter
import co.getpicks.lifecycleawarepresenter.presentation.base.BaseView
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance

abstract class BasePresenterActivity<P : BasePresenter<V>, V : BaseView> : KodeinAppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by instance()
    protected abstract val presenterClass: Class<P>

    protected val presenter: P by lazy(LazyThreadSafetyMode.NONE) {
        @Suppress("UNCHECKED_CAST")
        ViewModelProviders.of(this, viewModelFactory).get(presenterClass)
                .also { it.view = this as V }
    }

    @SuppressLint("MissingSuperCall") //https://github.com/SalomonBrys/Kodein/issues/101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
    }

    @SuppressLint("MissingSuperCall") //https://github.com/SalomonBrys/Kodein/issues/101
    override fun onDestroy() {
        lifecycle.removeObserver(presenter)
        super.onDestroy()
    }

}
