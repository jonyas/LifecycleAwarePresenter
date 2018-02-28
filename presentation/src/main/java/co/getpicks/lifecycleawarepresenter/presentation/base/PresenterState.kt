package co.getpicks.lifecycleawarepresenter.presentation.base

class PresenterState
constructor(
        private val onStateChangeListener: (BasePresenterStateValue?) -> Unit
) {

    var state: BasePresenterStateValue? = null
        set(value) {
            field = value
            onStateChangeListener(value)
        }

}