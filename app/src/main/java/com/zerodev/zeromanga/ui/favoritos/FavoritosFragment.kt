package com.zerodev.zeromanga.ui.favoritos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.AdapterMangasFavoritos
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import com.zerodev.zeromanga.data.remote.models.Manga
import com.zerodev.zeromanga.databinding.FavoritosFragmentBinding
import com.zerodev.zeromanga.listeners.MangaFavOnClickListener
import com.zerodev.zeromanga.utlities.constantes.ENVIAR_URL
import org.koin.android.ext.android.inject


class FavoritosFragment : Fragment(R.layout.favoritos_fragment) {

    private lateinit var binding : FavoritosFragmentBinding
    private lateinit var adapterFavoritos : AdapterMangasFavoritos
    companion object {
        fun newInstance() = FavoritosFragment()
    }

    private  val viewModel: FavoritosViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavoritosFragmentBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(FavoritosViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLoading()
        setUpRecyclerView(view)
    }


    private fun setUpLoading(){
        viewModel.isLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                hideProgressBar()
            }
            showProgressBar()
        })
    }
    private fun setUpRecyclerView(view: View){
        adapterFavoritos = AdapterMangasFavoritos(object : MangaFavOnClickListener {
            override fun OnClick(manga: Manga) {
                val bundle = Bundle()
                bundle.putString(ENVIAR_URL,
                    manga.mangaUrl)
                Navigation.findNavController(view).navigate(R.id.action_favoritosFragment_to_descripcionFragment,
                    bundle)
            }

        })

        binding.rvFavoritos.apply {
            setHasFixedSize(true)
            adapter = adapterFavoritos
        }

        viewModel.getMangasFav().observe(viewLifecycleOwner,{
            it.forEach { m->
                val manga  = m

                println(manga.title)
            }
            adapterFavoritos.differ.submitList(it)
            updateUI(it)
        })
    }

    private fun updateUI(mangasFav : List<MangaFav>) {
        if (mangasFav.isEmpty()){
            hideProgressBar()
            hideRecyclerView()
            showMessage()
        }else {
            showRecyclerView()
            hideMessage()
            hideProgressBar()


        }
    }

    private fun hideProgressBar(){
        binding.pbCargaFavoritos.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.pbCargaFavoritos.visibility = View.VISIBLE
    }

    private fun showMessage(){
        binding.tvNoSeEncontraronDatos.visibility = View.VISIBLE
        binding.tvNoSeEncontraronDatos.text = getString(R.string.not_data)
    }

    private fun hideMessage(){
        binding.tvNoSeEncontraronDatos.visibility = View.GONE
    }
    private fun hideRecyclerView(){
        binding.rvFavoritos.visibility = View.GONE
    }

    private fun showRecyclerView(){
        binding.rvFavoritos.visibility = View.VISIBLE
    }
}