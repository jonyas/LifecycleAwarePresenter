package co.getpicks.lifecycleawarepresenter.remote

import com.squareup.moshi.Json

data class PokemonList(
        @Json(name = "results") val items: List<PokemonListItemRemote>
)

data class PokemonListItemRemote(
        @Json(name = "url") val url: String,
        @Json(name = "name") val name: String
)

data class PokemonDetailsRemote(
        @Json(name = "name") val name: String,
        @Json(name = "sprites") val images: PokemonImagesRemote
)

data class PokemonImagesRemote(
        @Json(name = "front_default") val thumbnail: String
)
