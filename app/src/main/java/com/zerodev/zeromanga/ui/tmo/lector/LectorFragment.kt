package com.zerodev.zeromanga.ui.tmo.lector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.LectorAdapter
import com.zerodev.zeromanga.data.remote.models.Response
import com.zerodev.zeromanga.databinding.LectorFragmentBinding
import com.zerodev.zeromanga.utlities.constantes.NOMBRE_CAP
import com.zerodev.zeromanga.utlities.constantes.URL_IMAGE_CAP
import com.zerodev.zeromanga.utlities.constantes.URL_REFERER
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LectorFragment : Fragment() {

    companion object {
        fun newInstance() = LectorFragment()
    }


      private val viewModel: LectorViewModel by viewModels()

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

        setUpToolbar(view)
        if (urlImagen != null && mangaUrlRefer != null){
            viewLifecycleOwner.lifecycle.coroutineScope.launch {
                viewModel.getImagenesCap(urlImagen!!,mangaUrlRefer!!)
            }
        }
        /*
        * urlImagen?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getImagenesCap(it,mangaUrlRefer!!)
            }
        }
        * */
        TitleCap?.let {t->

        }
        viewModel.IsLoading().observe(viewLifecycleOwner, Observer {isLoading->
            if (isLoading){
                binding.pbCargarImagenesCapitulo.visibility = View.VISIBLE
            }
            else{
                binding.pbCargarImagenesCapitulo.visibility = View.GONE
                setUpData()
            }
        })

        viewModel.HasError().observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                showTextError()
            }
            hideTextError()
        }
    }

    private fun setUpToolbar(view: View){
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<Toolbar>(R.id.toolbarVisor).
                setupWithNavController(navController,appBarConfiguration)
    }
    private fun setUpData(){
        viewModel.getImagenes().observe(viewLifecycleOwner, Observer {imagenes ->
            if (imagenes.isNotEmpty()){
                lectorAdapter = LectorAdapter(imagenes,urlImagen!!)
                binding.vpImagenesCapitulos.adapter = lectorAdapter
            }else {
                showTextError()
            }
        })
    }
    private fun hideTextError(){
        binding.tvErrorDatos.visibility = View.GONE
    }

    private fun showTextError(){
        binding.tvErrorDatos.visibility = View.VISIBLE
    }
}