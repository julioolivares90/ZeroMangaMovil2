package com.zerodev.zeromanga.ui.tmo.lector

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.LectorAdapter
import com.zerodev.zeromanga.databinding.LectorFragmentBinding
import com.zerodev.zeromanga.ui.tmo.descripcion.DescripcionViewModel
import com.zerodev.zeromanga.utlities.constantes.URL_IMAGE_CAP
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class LectorFragment : Fragment() {

    companion object {
        fun newInstance() = LectorFragment()
    }

    private lateinit var viewModel: LectorViewModel

    private lateinit var binding : LectorFragmentBinding

    private lateinit var lectorAdapter: LectorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LectorFragmentBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(LectorViewModel::class.java)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments =  requireArguments()
        val urlImagen = arguments.getString(URL_IMAGE_CAP)
        urlImagen?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getImagenesCap(it)
            }
        }
        viewModel.IsLoading().observe(viewLifecycleOwner, Observer {
            if (it)
                binding.pbCargarImagenesCapitulo.visibility = View.VISIBLE
            else
                binding.pbCargarImagenesCapitulo.visibility = View.GONE
        })
        viewModel.getImagenes().observe(viewLifecycleOwner, Observer {
            lectorAdapter = LectorAdapter(it.toMutableList())

            binding.vpImagenesCapitulos.adapter = lectorAdapter
        })
    }
}