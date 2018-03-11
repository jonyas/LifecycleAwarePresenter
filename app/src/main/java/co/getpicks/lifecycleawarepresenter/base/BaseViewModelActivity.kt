package co.getpicks.lifecycleawarepresenter.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseViewModelActivity : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected inline fun <reified T : ViewModel> getViewModel() =
            ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

}
