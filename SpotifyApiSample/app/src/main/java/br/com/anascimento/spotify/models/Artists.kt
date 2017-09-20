package br.com.anascimento.spotify.models

import com.google.gson.annotations.SerializedName

/**
 * Created by andre.nascimento on 18/09/2017.
 */

class Artists {

    @SerializedName("items")
    val items : List<ArtistDetail>? = arrayListOf()
}
