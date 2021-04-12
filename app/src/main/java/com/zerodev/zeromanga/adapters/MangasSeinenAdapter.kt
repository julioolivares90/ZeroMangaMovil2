package com.zerodev.zeromanga.adapters
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.listeners.MangaOnclickListener
import com.zerodev.zeromanga.data.remote.models.Manga

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
        val item = mangas[position]
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

            val resources = itemView.resources
            //val verde = itemView.resources.getDrawable()

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
            }

            tipo.text = manga.type




            score.text = manga.score
            Glide.with(itemView.context)
                .load(manga.mangaImagen).into(imagen)
            itemView.setOnClickListener {
                onClickListener.onClick(manga = manga)
            }
        }
    }
}