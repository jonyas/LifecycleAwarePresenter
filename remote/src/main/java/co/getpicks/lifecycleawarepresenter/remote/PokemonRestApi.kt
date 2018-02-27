package co.getpicks.lifecycleawarepresenter.remote

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonRestApi {

    @GET("pokemon")
    fun loadAllPokemons(): Single<PokemonList>

    @GET("pokemon/{pokemonId}")
    fun loadPokemon(@Path("pokemonId") id: Int): Single<PokemonDetailsRemote>

}

object PokemonRestApiBuilder {

    fun build(baseUrl: String): PokemonRestApi {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        MoshiConverterFactory.create(
                                Moshi.Builder()
                                        .add(KotlinJsonAdapterFactory())
                                        .build()
                        )
                ).baseUrl(baseUrl)
                .client(OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                ).build()
                .create(PokemonRestApi::class.java)
    }

}