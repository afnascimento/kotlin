package br.com.anascimento.spotify.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.anascimento.spotify.R
import br.com.anascimento.spotify.models.ArtistDetail
import com.squareup.picasso.Picasso
import org.apache.commons.lang3.StringUtils
import java.util.ArrayList

/**
 * Created by andre.nascimento on 18/09/2017.
 */
class ArtistSearchAdapter : RecyclerView.Adapter<ArtistSearchAdapter.ViewHolder>() {

    val lista : ArrayList<ArtistDetail> = arrayListOf()

    fun setLista(items: ArrayList<ArtistDetail>?) {
        lista?.clear()
        if (items != null) {
            lista?.addAll(items)
        }
    }

    private fun getItem(position: Int): ArtistDetail {
        return lista[position]
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItems(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_artist_item, parent, false)
        return ArtistSearchAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(artistDetail: ArtistDetail) {
            val imageArtist = itemView.findViewById<ImageView>(R.id.imageArtist) as ImageView
            val tvArtistName = itemView.findViewById<TextView>(R.id.tvArtistName) as TextView
            val imageUrl = artistDetail.getUrlImage()
            if (StringUtils.isNotBlank(imageUrl)) {
                Picasso.with(itemView.context)
                        .load(artistDetail.getUrlImage())
                        .into(imageArtist)
            }
            tvArtistName.setText(StringUtils.trimToEmpty(artistDetail.name))
        }
    }
}