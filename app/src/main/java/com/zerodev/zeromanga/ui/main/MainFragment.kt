package com.zerodev.zeromanga.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.MangasPopularesAdapter
import com.zerodev.zeromanga.adapters.MangasSeinenAdapter
import com.zerodev.zeromanga.data.remote.models.*
import com.zerodev.zeromanga.databinding.MainFragmentBinding
import com.zerodev.zeromanga.listeners.MangaOnclickListener
import com.zerodev.zeromanga.utlities.constantes.ENVIAR_URL


import com.zerodev.zeromanga.utlities.CheckNetwork
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private lateinit var adapter : MangasSeinenAdapter
    private lateinit var adapterPopulares : MangasPopularesAdapter

    private lateinit var binding: MainFragmentBinding
    private  val mainViewModel : MainViewModel by inject()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainFragmentBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //muestra un progressbar si esta cargando y esconde los componentes


        val checkNetwork = CheckNetwork(requireContext())

        checkNetwork.observe(viewLifecycleOwner){
            if (it){
                binding.nestedScroll.visibility = View.VISIBLE
                binding.showError.root.visibility = View.GONE
                setMangaData(view)
            }else {
                hideComponents()
                binding.showError.root.visibility = View.VISIBLE
            }
        }
    }

    private  fun setMangaData (view: View) {
        //mainViewModel.setMangaData()
        mainViewModel.getMangaData().observe(viewLifecycleOwner, Observer {
            when(it){
                is ResponseManga.Loading->{
                    hideMessageError()
                    showProgressbar()
                    hideComponents()

                }
                is ResponseManga.Success->{
                    hideProgressBar()
                    showComponents()
                    SetData(view)
                    hideMessageError()
                }
                is ResponseManga.Error ->{
                    showMessageError()
                    hideProgressBar()
                    hideComponents()
                }
            }
        })
    }

    private fun SetData(view: View) {
        mainViewModel.getMangaData().observe(viewLifecycleOwner, Observer {
            when(it){
                is ResponseManga.Success->{
                    //adapter mangas seinen
                    adapter = MangasSeinenAdapter(it.data.mangasSeinen,mangaOnclickListener = object : MangaOnclickListener{
                        override fun onClick(manga: Manga) {
                            val bundle = Bundle()
                            bundle.putString(ENVIAR_URL,manga.mangaUrl)

                            Navigation
                                .findNavController(view)
                                .navigate(R.id.action_mainFragment_to_descripcionFragment
                                    ,bundle)
                        }

                    })

                    //adapter mangas populares
                    adapterPopulares = MangasPopularesAdapter(it.data.mangasPopulares,mangaOnclickListener = object : MangaOnclickListener{
                        override fun onClick(manga: Manga) {
                            val bundle = Bundle()
                            bundle.putString(ENVIAR_URL,manga.mangaUrl)

                            Navigation
                                .findNavController(view)
                                .navigate(R.id.action_mainFragment_to_descripcionFragment
                                    ,bundle)
                        }
                    })

                    binding.rvMangasSeinen.adapter = adapter
                    binding.rvMangasPopulares.adapter = adapterPopulares
                }
                is ResponseManga.Error-> {

                }
            }
        })
    }
    private fun showProgressbar(){
        binding.pbCargarMangas.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        binding.pbCargarMangas.visibility = View.GONE
    }

    private fun hideComponents(){
        binding.textView.visibility = View.GONE
        binding.rvMangasSeinen.visibility = View.GONE
        binding.rvMangasPopulares.visibility = View.GONE
        binding.tvSeinen.visibility = View.GONE
    }

    private fun showComponents(){
        binding.textView.visibility = View.VISIBLE
        binding.rvMangasSeinen.visibility = View.VISIBLE
        binding.rvMangasPopulares.visibility = View.VISIBLE
        binding.tvSeinen.visibility = View.VISIBLE
    }

    private fun showMessageError(){
        binding.tvError.visibility = View.VISIBLE
    }

    private fun hideMessageError(){
        binding.tvError.visibility = View.GONE
    }
}