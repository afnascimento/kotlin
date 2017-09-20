package br.com.anascimento.spotify.service

import br.com.anascimento.spotify.models.SearchSpotify
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by andre.nascimento on 18/09/2017.
 */
interface SpotifySearchService {

    @GET("search")
    fun searchItems(@Header("authorization") auth: String?,
                    @Query("q") query: String?,
                    @Query("type") type: String?) : Call<SearchSpotify>
}