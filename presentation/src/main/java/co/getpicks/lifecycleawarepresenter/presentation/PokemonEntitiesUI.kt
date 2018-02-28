package co.getpicks.lifecycleawarepresenter.presentation

data class PokemonUI(
        val id: Int,
        val name: String,
        val image: String,
        var isFavorite: Boolean
)