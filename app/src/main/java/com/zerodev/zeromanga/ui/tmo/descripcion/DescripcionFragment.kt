package com.zerodev.zeromanga.ui.tmo.descripcion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.AdapterCapitulos
import com.zerodev.zeromanga.adapters.DescripcionViewPagerAdapter
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import com.zerodev.zeromanga.data.remote.models.Capitulo
import com.zerodev.zeromanga.databinding.DescripcionFragmentBinding
import com.zerodev.zeromanga.data.remote.models.MangaResponse
import com.zerodev.zeromanga.listeners.CapituloOnClickListener
import com.zerodev.zeromanga.listeners.SnackBarClickListener
import com.zerodev.zeromanga.utlities.constantes
import com.zerodev.zeromanga.utlities.constantes.ENVIAR_URL
import org.koin.android.ext.android.inject


class DescripcionFragment : Fragment(R.layout.descripcion_fragment) {

    companion object {
        fun newInstance() = DescripcionFragment()
    }

    private  val viewModel: DescripcionViewModel by inject()

    private lateinit var binding: DescripcionFragmentBinding

    private lateinit var descripcionViewPagerAdapter : DescripcionViewPagerAdapter

    private lateinit var mangaResponse: MangaResponse

    private lateinit var mangaUrlRefer : String

    private lateinit var urlCapitulo : String

    private lateinit var adapterCapitulo: AdapterCapitulos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DescripcionFragmentBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(DescripcionViewModel::class.java)

        val arguments = arguments

        val mangaUrl = arguments?.getString(ENVIAR_URL)


        mangaUrl?.let {
            urlCapitulo = it
        }
        mangaUrl?.let {
            viewModel.setInfoManga(it)
            mangaUrlRefer = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //modificar la toolbar
        setUpToolbar(view)


        viewModel.IsLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                binding.pbDescripcion.visibility = View.VISIBLE
                hideComponents()
            }
            else{
                binding.pbDescripcion.visibility = View.GONE
                showComponents()
                setUpInformacion(view)
            }
        })

        viewModel.MangaExist().observe(viewLifecycleOwner,{existe->
            if (existe){
               val snackbar = Snackbar.make(view,"Manga ya se encuentra en tus favoritos",Snackbar.LENGTH_SHORT)

                snackbar.setAction(R.string.error_favoritos,SnackBarClickListener())
                snackbar.show()
            }
        })
        binding.btnFav.setOnClickListener {
            addMangaToFavorites(view)
        }

        binding.btnMasDetalles.setOnClickListener {
            val directions = DescripcionFragmentDirections.actionDescripcionFragmentToDetalleFragment(mangaResponse.data)
            Navigation.findNavController(view).navigate(directions)
        }
    }

    private fun setUpToolbar(view: View){
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navGraph = navController.graph)
        view.findViewById<Toolbar>(R.id.toolbarDescripcion)
            .setupWithNavController(navController,appBarConfiguration)
    }
    fun setUpInformacion(view: View){
        viewModel.getInfoManga().observe(viewLifecycleOwner,{
            mangaResponse = it

            Glide.with(view).load(mangaResponse.data.imageUrl).into(binding.ivDetalleManga)

            //binding.tvTitleManga.text = mangaResponse.data.title
            Log.d("TITLE -> ",it.data.title)
            Log.d("TITLE MResponse ->",mangaResponse.data.title)
            //binding.tvTitleManga.text = mangaResponse.data.title
            binding.collapsionToolbar.title = mangaResponse.data.title
            //binding.toolbarDescripcion.setTitle(mangaResponse.data.title)
            //view.findViewById<Toolbar>(R.id.toolbarDescripcion).apply { title = mangaResponse.data.title }

            val existe = viewModel.mangaExiste(it.data.title)
            if (existe){
                binding.btnFav.isEnabled = false
            }
            setUpRecyclerView(mangaResponse.data.capitulos,view)
        })
    }

    fun setUpRecyclerView(capitulos : List<Capitulo>,view: View){
        adapterCapitulo = AdapterCapitulos(capitulos,object : CapituloOnClickListener{
            override fun onClick(capitulo: Capitulo) {
                val bundle = Bundle()
                bundle.putString(constantes.URL_IMAGE_CAP,capitulo.urlLeer)
                bundle.putString(constantes.NOMBRE_CAP,capitulo.name)
                bundle.putString(constantes.URL_REFERER,mangaUrlRefer)
                Navigation.findNavController(view).navigate(R.id.action_descripcionFragment_to_lectorFragment,bundle)
            }

        })

        binding.rvCapitulos.apply {
            adapter = adapterCapitulo
            layoutManager =LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(object : DividerItemDecoration(
                activity,LinearLayoutManager.VERTICAL
            ){})
        }
    }
    fun setUpViewPager() {
        viewModel.getInfoManga().observe(viewLifecycleOwner, Observer {
            mangaResponse = it


            //(activity as AppCompatActivity).setSupportActionBar()
            //val toolbar = (activity as AppCompatActivity).supportActionBar

           // toolbar?.title = mangaResponse.data.title
           /*
            val tb_imagen= toolbar?.customView?.findViewById<ImageView>(R.id.tb_imagen)
            tb_imagen?.let {imagen ->
                Glide.with(requireContext()).load(mangaResponse.data.image).into(imagen)
            }

            */

            //Glide.with(requireContext()).load(mangaResponse.data.image).into(binding.imageView)
            descripcionViewPagerAdapter = DescripcionViewPagerAdapter(mangaResponse = mangaResponse,mangaUrlRefer = mangaUrlRefer ,this)

            //binding.viewPagerDescripcion.adapter = descripcionViewPagerAdapter

            /*
            *
            TabLayoutMediator(binding.tabLayout,binding.viewPagerDescripcion,
                TabLayoutMediator.TabConfigurationStrategy{ tab: TabLayout.Tab, position: Int ->
                    when(position){
                        0-> {
                            tab.text = "Informacion"
                            tab.icon = resources.getDrawable(R.drawable.ic_baseline_recent_actors_24)
                        }
                        1-> {
                            tab.text = "Capitulo"
                            tab.icon = resources.getDrawable(R.drawable.ic_baseline_list_24)
                        }
                    }
                }).attach()
            *
            * */
        })
    }

    fun destroyViewPager(){
        //binding.viewPagerDescripcion.adapter = null
    }

    fun hideComponents(){
        binding.llButtons.apply {
            visibility = View.GONE
        }

    }

    fun showComponents(){
        binding.llButtons.apply {
            visibility = View.VISIBLE
        }
    }

    //override methods
    override fun onPause() {
        super.onPause()
        destroyViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyViewPager()
    }

    override fun onResume() {
        super.onResume()
        //setUpViewPager()
    }

    fun addMangaToFavorites(view: View){

        val title = mangaResponse.data.title

        val imagen = mangaResponse.data.imageUrl

        val score = mangaResponse.data.score

        val tipo = mangaResponse.data.tipo

        val demografia = mangaResponse.data.demografia

        val descripcion = mangaResponse.data.description

        val url = urlCapitulo

        val mangaFav = MangaFav(0,title, imagen, score, tipo, demografia, descripcion, url)
        viewModel.addMangaToFavorites(mangaFav = mangaFav)

        Snackbar.make(view,"Manga agregado a favoritos",Snackbar.LENGTH_SHORT).setAction("quieres ver tus favoritos") {

        }.show()
    }

    fun comprobarVariables(urlCap : String, urlRefer: String)  : Boolean{
        if (urlCap.isNotEmpty() && urlRefer.isNotEmpty())
            return true
        return false
    }
}