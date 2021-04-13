package com.zerodev.zeromanga.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import com.zerodev.zeromanga.databinding.RowFavoritosBinding
import com.zerodev.zeromanga.utlities.constantes.ENVIAR_URL

class AdapterMangasFavoritos  : RecyclerView.Adapter<AdapterMangasFavoritos.MangaHoder>() {

    private var binding : RowFavoritosBinding? = null

    private val differCallback = object : DiffUtil.ItemCallback<MangaFav>() {
        override fun areItemsTheSame(oldItem: MangaFav, newItem: MangaFav): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MangaFav, newItem: MangaFav): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

     class MangaHoder(itemBinding: RowFavoritosBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaHoder {
        binding = RowFavoritosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MangaHoder(binding!!)
    }

    override fun onBindViewHolder(holder: MangaHoder, position: Int) {
        val mangaFav = differ.currentList[position]

        holder.itemView.apply {

            binding?.tvFavoritoTitle?.text = mangaFav.title
            binding?.tvDescripcionFavoritos?.text = mangaFav.descripcion
            Glide.with(holder.itemView).load(mangaFav.imagen).into(binding!!.ivFavoritos)

        }.setOnClickListener {
            /**
             *  val manga = Manga(
                title = mangaFav.title,
                demography = mangaFav.demografia,
                score = mangaFav.score,
                type = mangaFav.tipo,
                mangaUrl = mangaFav.url,
                mangaImagen = mangaFav.imagen
            )
             */

            val bundle = Bundle()
            bundle.putString(ENVIAR_URL,mangaFav.url)
            Navigation.findNavController(it).navigate(R.id.action_favoritosFragment_to_descripcionFragment,bundle)
        }
    }

    override fun getItemCount() = differ.currentList.size
}