package com.zerodev.zeromanga.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.ui.tmo.descripcion.capitulo.CapituloFragment
import com.zerodev.zeromanga.ui.tmo.descripcion.detalle.DetalleFragment

class DescripcionViewPagerAdapter(val mangaResponse: MangaResponse,val mangaUrlRefer : String ="",fragment: Fragment) : FragmentStateAdapter(fragment) {


    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {

        val  inicio = DetalleFragment.newInstance(mangaResponse)
        if (position == 0){
            val fragment = DetalleFragment.newInstance(mangaResponse)
            return fragment
        }else if (position == 1){
            val fragment = CapituloFragment.newInstance(mangaResponse,mangaUrlRefer)
            return fragment
        }
        return inicio
    }
}