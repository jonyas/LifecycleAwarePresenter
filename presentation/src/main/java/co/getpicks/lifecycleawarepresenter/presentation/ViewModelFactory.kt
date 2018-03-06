package co.getpicks.lifecycleawarepresenter.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelFactory
constructor(
        private val creators: Map<Class<out ViewModel>, ViewModel>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var viewModel: ViewModel? = creators[modelClass]
        if (viewModel == null) {
            for ((key, value) in creators.entries) {
                if (modelClass.isAssignableFrom(key)) {
                    viewModel = value
                    break
                }
            }
        }
        if (viewModel == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            return viewModel as T
        } catch (e: ClassCastException) {
            throw ClassCastException("$viewModel cannot be cast!")
        }
    }
}