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
import com.zerodev.zeromanga.data.remote.models.Capitulo
import com.zerodev.zeromanga.data.remote.models.MangaResponse
import com.zerodev.zeromanga.utlities.constantes.NOMBRE_CAP
import com.zerodev.zeromanga.utlities.constantes.URL_IMAGE_CAP
import com.zerodev.zeromanga.utlities.constantes.URL_REFERER

class CapituloFragment : Fragment() {

    private lateinit var mangaResponse: MangaResponse

    private  lateinit var mangaUrlRefer: String

    lateinit var binding: CapituloFragmentBinding

    private lateinit var viewModel: CapituloViewModel

    private lateinit var adapterCapitulo: AdapterCapitulos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CapituloFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CapituloViewModel::class.java]
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

            adapterCapitulo = AdapterCapitulos(it.data.capitulos,
                object : CapituloOnClickListener {
                    override fun onClick(capitulo: Capitulo) {
                        val bundle = Bundle()
                        bundle.putString(URL_IMAGE_CAP,capitulo.urlLeer)
                        bundle.putString(NOMBRE_CAP,capitulo.name)
                        bundle.putString(URL_REFERER,mangaUrlRefer)
                        Navigation.findNavController(view).navigate(R.id.action_descripcionFragment_to_lectorFragment,bundle)
                    }
                })

            //adapterCapitulo.differ.submitList(it.data.capitulo.toMutableList())
            binding.rvCapitulos.adapter = adapterCapitulo
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(mangaResponse: MangaResponse, mangaUrlRefer : String = "") = CapituloFragment().apply {
            this.mangaResponse = mangaResponse
            this.mangaUrlRefer = mangaUrlRefer
        }
    }
}