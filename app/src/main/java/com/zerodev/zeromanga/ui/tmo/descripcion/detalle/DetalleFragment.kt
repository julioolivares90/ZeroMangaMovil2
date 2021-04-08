package com.zerodev.zeromanga.ui.tmo.descripcion.detalle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.AdapterGeneros
import com.zerodev.zeromanga.databinding.FragmentDetalleBinding
import com.zerodev.zeromanga.net.models.MangaResponse


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DetalleFragment() : Fragment(R.layout.fragment_detalle) {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mangaResponse: MangaResponse

    private lateinit var adapterGeneros : AdapterGeneros

    private lateinit var binding : FragmentDetalleBinding

    private lateinit var viewModel: DetalleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        mangaResponse.let {
            //Glide.with(view).load(mangaResponse.data.image).into(imagenFondo)
            viewModel.mangaResponse.value = it
        }

        viewModel.mangaResponse.observe(viewLifecycleOwner, Observer {mangaResponse->

            Glide.with(view).load(mangaResponse.data.image).into(binding.imageView)
           // tv_title_descripcion.text = mangaResponse.data.title
            binding.tvDescripcion.text = mangaResponse.data.descripcion
            binding.tvDemografia.text = mangaResponse.data.demografia
            binding.tvEstado.text = mangaResponse.data.estado
            binding.tvScore.text = mangaResponse.data.score
            binding.tvTipo.text = mangaResponse.data.tipo

            adapterGeneros = AdapterGeneros()
            adapterGeneros.differ.submitList(mangaResponse.data.generos.toMutableList())
            binding.rvGeneros.adapter = adapterGeneros
        })
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetalleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        @JvmStatic
        fun newInstance(mangaResponse: MangaResponse) = DetalleFragment().apply {
            this.mangaResponse = mangaResponse
        }

    }
}