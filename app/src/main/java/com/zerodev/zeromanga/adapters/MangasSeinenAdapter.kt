package com.zerodev.zeromanga.adapters
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

class MangasSeinenAdapter (var mangas : MutableList<Manga>
                           ,var mangaOnclickListener: MangaOnclickListener) : RecyclerView.Adapter<MangasSeinenAdapter.MangaSeinenViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaSeinenViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.populares_row
                ,parent,false)

        return MangaSeinenViewHolder(itemView)
    }

    override fun getItemCount() = mangas.size

    override fun onBindViewHolder(holder: MangaSeinenViewHolder, position: Int) {
        val item = mangas.get(position)
        holder.bind(manga = item,onClickListener = mangaOnclickListener)
    }

    inner class MangaSeinenViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val title : TextView = itemView.findViewById(R.id.tv_title_popular)
        private val demografia : TextView = itemView.findViewById(R.id.tv_demografia_popular)
        private val tipo : TextView = itemView.findViewById(R.id.tv_tipo_popular)
        private val score : TextView = itemView.findViewById(R.id.tv_score_popular)
        private val imagen : ImageView = itemView.findViewById(R.id.iv_manga_popular)


        fun bind(manga : Manga, onClickListener: MangaOnclickListener){
            title.text = manga.title
            demografia.text = manga.demography

            when(manga.demography){
                "Josei"-> demografia.setBackgroundColor(R.drawable.backgroundtipoverde)
                "Seinen" -> demografia.setBackgroundColor(R.drawable.backgroundcolorrojo)
                "Shounen" -> demografia.setBackgroundColor(R.drawable.backgrounddemografiashounen)
                "Shoujo"-> demografia.setBackgroundColor(R.drawable.backgroundrosado)
            }


            tipo.text = manga.type

            when(manga.type){
                "MANGA"-> tipo.setBackgroundColor(R.drawable.backgroundazul)
                "MANHWA"->tipo.setBackgroundColor(R.drawable.backgroundtipoverde)
                "MANHUA"->tipo.setBackgroundColor(R.drawable.backgroundcafe)
            }


            score.text = manga.score
            Glide.with(itemView.context)
                .load(manga.mangaImagen).into(imagen)
            itemView.setOnClickListener {
                onClickListener.onClick(manga = manga)
            }
        }
    }
}