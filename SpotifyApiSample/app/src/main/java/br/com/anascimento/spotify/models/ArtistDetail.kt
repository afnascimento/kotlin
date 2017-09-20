package br.com.anascimento.spotify.models

import org.apache.commons.lang3.StringUtils

/**
 * Created by andre.nascimento on 18/09/2017.
 */

class ArtistDetail {

    val id: String? = null

    val name: String? = null

    val images : ArrayList<ImagesDetail>? = ArrayList()

    override fun toString(): String {
        return "ArtistDetail(id=$id, name=$name)"
    }

    fun getUrlImage() : String {
        var url = ""
        if (images != null && !images.isEmpty()) {
            url = StringUtils.trimToEmpty(images.get(0).url)
        }
        return url
    }
}