package com.zerodev.zeromanga.ui.tmo.descripcion.capitulo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.AdapterCapitulos
import com.zerodev.zeromanga.databinding.CapituloFragmentBinding
import com.zerodev.zeromanga.listeners.CapituloOnClickListener
import com.zerodev.zeromanga.net.models.Capitulo
import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.utlities.constantes.NOMBRE_CAP
import com.zerodev.zeromanga.utlities.constantes.URL_IMAGE_CAP

class CapituloFragment : Fragment() {

    private lateinit var mangaResponse: MangaResponse

    lateinit var binding: CapituloFragmentBinding

    private lateinit var viewModel: CapituloViewModel

    private lateinit var adapterCapitulo: AdapterCapitulos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CapituloFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CapituloViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mangaResponse.let {
            adapterCapitulo = AdapterCapitulos(it.data.capitulo.toMutableList(),
                object : CapituloOnClickListener {
                    override fun onClick(capitulo: Capitulo) {
                        val bundle = Bundle()
                        bundle.putString(URL_IMAGE_CAP,capitulo.UrlLeer)
                        bundle.putString(NOMBRE_CAP,capitulo.Title)
                        Navigation.findNavController(view).navigate(R.id.action_descripcionFragment_to_lectorFragment,bundle)
                    }
                })
            binding.rvCapitulos.adapter = adapterCapitulo
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(mangaResponse: MangaResponse) = CapituloFragment().apply {
            this.mangaResponse = mangaResponse
        }
    }
}