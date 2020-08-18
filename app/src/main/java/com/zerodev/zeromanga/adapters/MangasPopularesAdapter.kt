package com.zerodev.zeromanga.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.listeners.MangaOnclickListener
import com.zerodev.zeromanga.net.models.Manga

class MangasPopularesAdapter(val mangas : MutableList<Manga>
                             ,val mangaOnclickListener: MangaOnclickListener) : RecyclerView.Adapter<MangasPopularesAdapter.MangasPopularesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangasPopularesViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.populares_row,parent,false)
        return  MangasPopularesViewHolder(view)
    }

    override fun getItemCount() = mangas.size

    override fun onBindViewHolder(holder: MangasPopularesViewHolder, position: Int) {
        val item = mangas[position]
        holder.bind(manga = item,mangaOnclickListener = mangaOnclickListener)
    }

    inner class MangasPopularesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val title : TextView = itemView.findViewById(R.id.tv_title_popular)
        private val demografia : TextView = itemView.findViewById(R.id.tv_demografia_popular)
        private val tipo : TextView = itemView.findViewById(R.id.tv_tipo_popular)
        private val score : TextView = itemView.findViewById(R.id.tv_score_popular)
        private val imagen : ImageView = itemView.findViewById(R.id.iv_manga_popular)

        fun bind(manga: Manga,mangaOnclickListener: MangaOnclickListener){
            title.text = manga.title
            demografia.text = manga.demography

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                when(manga.demography){
                    "Josei"-> demografia.setTextAppearance(R.style.textJosei) //demografia.setBackgroundColor(R.drawable.backgroundtipoverde)
                    "Seinen" -> demografia.setTextAppearance(R.style.textSeinen)//demografia.setBackgroundColor(R.drawable.backgroundcolorrojo)
                    "Shounen" -> demografia.setTextAppearance(R.style.textShounen)//demografia.setBackgroundColor(R.drawable.backgrounddemografiashounen)
                    "Shoujo"-> demografia.setTextAppearance(R.style.textShoujo)//demografia.setBackgroundColor(R.drawable.backgroundrosado)
                }
                when(manga.type){
                    "MANGA"->  demografia.setTextAppearance(R.style.tipoManga)//tipo.setBackgroundColor(R.drawable.backgroundazul)
                    "MANHWA"-> demografia.setTextAppearance(R.style.tipoManhwa)//tipo.setBackgroundColor(R.drawable.backgroundtipoverde)
                    "MANHUA"-> demografia.setTextAppearance(R.style.tipoManhua)//tipo.setBackgroundColor(R.drawable.backgroundcafe)
                }
            }else if (Build.VERSION.SDK_INT <= 23){
                when(manga.demography){
                    "Josei"-> demografia.setTextAppearance(itemView.context,R.style.textJosei) //demografia.setBackgroundColor(R.drawable.backgroundtipoverde)
                    "Seinen" -> demografia.setTextAppearance(itemView.context,R.style.textSeinen)//demografia.setBackgroundColor(R.drawable.backgroundcolorrojo)
                    "Shounen" -> demografia.setTextAppearance(itemView.context,R.style.textShounen)//demografia.setBackgroundColor(R.drawable.backgrounddemografiashounen)
                    "Shoujo"-> demografia.setTextAppearance(itemView.context,R.style.textShoujo)//demografia.setBackgroundColor(R.drawable.backgroundrosado)
                }
                when(manga.type){
                    "MANGA"->  demografia.setTextAppearance(itemView.context,R.style.tipoManga)//tipo.setBackgroundColor(R.drawable.backgroundazul)
                    "MANHWA"-> demografia.setTextAppearance(itemView.context,R.style.tipoManhwa)//tipo.setBackgroundColor(R.drawable.backgroundtipoverde)
                    "MANHUA"-> demografia.setTextAppearance(itemView.context,R.style.tipoManhua)//tipo.setBackgroundColor(R.drawable.backgroundcafe)
                }
            }

            tipo.text = manga.type




            score.text = manga.score
            Glide.with(itemView.context)
                .load(manga.mangaImagen).into(imagen)
            itemView.setOnClickListener {
                mangaOnclickListener.onClick(manga = manga)
            }
        }
    }

}