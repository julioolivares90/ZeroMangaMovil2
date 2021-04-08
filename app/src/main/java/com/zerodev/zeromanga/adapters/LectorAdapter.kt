package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.databinding.VisorItemBinding

class LectorAdapter : RecyclerView.Adapter<LectorAdapter.LectorViewHolder>() {

    private var binding : VisorItemBinding? = null

    private val differCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectorViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.visor_item,parent,false)
        binding = VisorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LectorViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: LectorViewHolder, position: Int) {
        val imagen = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this.context).load(imagen).into(binding!!.pvImagenCapitulo)
        }
    }

    override fun getItemCount() = differ.currentList.size

     class LectorViewHolder(itemBinding: VisorItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
       /*
        private val photoView : PhotoView = itemView.findViewById(R.id.pv_imagen_capitulo)

        fun bind( imagen : String){
            Glide
                .with(itemView
                    .context)
                .load(imagen)
                .into(photoView)
        }
        */
    }
}