package com.zerodev.zeromanga.ui.tmo.lector

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.LectorAdapter
import com.zerodev.zeromanga.databinding.LectorFragmentBinding
import com.zerodev.zeromanga.ui.tmo.descripcion.DescripcionViewModel
import com.zerodev.zeromanga.utlities.constantes.NOMBRE_CAP
import com.zerodev.zeromanga.utlities.constantes.URL_IMAGE_CAP
import com.zerodev.zeromanga.utlities.constantes.URL_REFERER
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.coroutineContext


class LectorFragment : Fragment() {

    companion object {
        fun newInstance() = LectorFragment()
    }

    private  val viewModel: LectorViewModel  by inject()

    private lateinit var binding : LectorFragmentBinding

    private lateinit var lectorAdapter: LectorAdapter

    private  var urlImagen : String? = null

    private  var TitleCap : String? = null

    private var mangaUrlRefer : String? = null

    /*
     val pref = activity?.getSharedPreferences(getString(R.string.my_shared_preference),
        MODE_PRIVATE)

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LectorFragmentBinding.inflate(layoutInflater)

        //viewModel = ViewModelProvider(this).get(LectorViewModel::class.java)

        val arguments = requireArguments()
        urlImagen = arguments.getString(URL_IMAGE_CAP)
        TitleCap = arguments.getString(NOMBRE_CAP)
        mangaUrlRefer = arguments.getString(URL_REFERER)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val arguments =  requireArguments()
        //val urlImagen = arguments.getString(URL_IMAGE_CAP)

        urlImagen?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getImagenesCap(it,mangaUrlRefer!!)
            }
        }
        TitleCap?.let {t->
            val myToolbar = (activity as AppCompatActivity).supportActionBar
            myToolbar?.let {actionBar->
                actionBar.title = t
            }
        }
        viewModel.IsLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                binding.pbCargarImagenesCapitulo.visibility = View.VISIBLE
            }
            else{
                binding.pbCargarImagenesCapitulo.visibility = View.GONE
            }

        })
        viewModel.getImagenes().observe(viewLifecycleOwner, Observer {
            lectorAdapter = LectorAdapter()
            lectorAdapter.differ.submitList(it.toMutableList())

            binding.vpImagenesCapitulos.adapter = lectorAdapter
        })
    }
}