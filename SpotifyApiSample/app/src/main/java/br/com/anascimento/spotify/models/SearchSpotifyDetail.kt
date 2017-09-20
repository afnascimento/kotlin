package br.com.anascimento.spotify.models

import com.google.gson.annotations.SerializedName

/**
 * Created by andre.nascimento on 18/09/2017.
 */
class SearchSpotifyDetail {

    val href: String? = null

    var limit: Int = 0

    var offset: Int = 0

    var total: Int = 0

    @SerializedName("items")
    val items : ArrayList<ArtistDetail>? = ArrayList()
}