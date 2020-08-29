package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.net.models.Manga

class AdapterMangaBusqueda(val mangas : MutableList<Manga>) : RecyclerView.Adapter<AdapterMangaBusqueda.MangaBusquedaViewHolder>(){


    class MangaBusquedaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val iv_manga_buscar : ImageView = itemView.findViewById(R.id.iv_manga_buscar)
        private val tv_title_manga_busqueda : TextView = itemView.findViewById(R.id.tv_title_manga_busqueda)
        private val tv_score_manga_busqueda : TextView = itemView.findViewById(R.id.tv_score_manga_busqueda)
        private val tv_type_manga_busqueda : TextView = itemView.findViewById(R.id.tv_type_manga_busqueda)
        private val tv_demografia_manga_busqueda : TextView = itemView.findViewById(R.id.tv_demografia_manga_busqueda)

        fun bind(manga: Manga){

            tv_title_manga_busqueda.text = manga.title
            tv_score_manga_busqueda.text = manga.score
            tv_type_manga_busqueda.text = manga.type
            tv_demografia_manga_busqueda.text = manga.demography

            Glide.with(itemView).load(manga.mangaImagen).into(iv_manga_buscar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaBusquedaViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_manga_busqueda,parent,false)

        return MangaBusquedaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MangaBusquedaViewHolder, position: Int) {
       val item  = mangas[position]
        holder.bind(manga = item)
    }

    override fun getItemCount() = mangas.size
}