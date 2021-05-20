package com.zerodev.zeromanga.ui.tmo.descripcion.detalle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.AdapterGeneros
import com.zerodev.zeromanga.data.remote.models.InfoManga
import com.zerodev.zeromanga.databinding.FragmentDetalleBinding
import com.zerodev.zeromanga.data.remote.models.MangaResponse





class DetalleFragment() : Fragment(R.layout.fragment_detalle) {



    private lateinit var mangaResponse: InfoManga

    private lateinit var adapterGeneros : AdapterGeneros

    private lateinit var binding : FragmentDetalleBinding

    private lateinit var viewModel: DetalleViewModel

    private val args: DetalleFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentDetalleBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(DetalleViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //antiguo
        //return inflater.inflate(R.layout.fragment_detalle, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar(view = view)

        mangaResponse = args.mangainfo!!

        setUpInformacion(view = view,mangaResponse = mangaResponse)
    }

    private fun setUpToolbar(view: View){
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<Toolbar>(R.id.toolbarDetalle)
            .setupWithNavController(navController,appBarConfiguration)
    }

    private fun setUpInformacion(view: View,mangaResponse: InfoManga){
        Glide.with(view).load(mangaResponse.imageUrl).into(binding.ivDetalleManga)
        // tv_title_descripcion.text = mangaResponse.data.title
        binding.toolbarDetalle.title = mangaResponse.title
        binding.tvDescripcion.text = mangaResponse.description
        binding.tvDemografia.text = mangaResponse.demografia
        binding.tvEstado.text = mangaResponse.estado
        binding.tvScore.text = mangaResponse.score
        binding.tvTipo.text = mangaResponse.tipo

        adapterGeneros = AdapterGeneros()
        adapterGeneros.differ.submitList(mangaResponse.generos.toMutableList())
        binding.rvGeneros.adapter = adapterGeneros
    }

}