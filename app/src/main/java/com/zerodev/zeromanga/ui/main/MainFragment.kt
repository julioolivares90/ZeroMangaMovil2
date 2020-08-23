package com.zerodev.zeromanga.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.MangasPopularesAdapter
import com.zerodev.zeromanga.adapters.MangasSeinenAdapter
import com.zerodev.zeromanga.databinding.MainFragmentBinding
import com.zerodev.zeromanga.listeners.MangaOnclickListener
import com.zerodev.zeromanga.net.models.Manga
import com.zerodev.zeromanga.net.models.Resource
import com.zerodev.zeromanga.net.models.Response
import com.zerodev.zeromanga.net.models.ResponseManga
import com.zerodev.zeromanga.utlities.constantes.ENVIAR_URL

class MainFragment : Fragment() {

    private lateinit var adapter : MangasSeinenAdapter
    private lateinit var adapterPopulares : MangasPopularesAdapter

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.IsLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                hideComponents()
                showProgressbar()
            }else {
                hideProgressBar()
                showComponents()
            }
        })

        viewModel.getMangaSeinen().observe(viewLifecycleOwner, Observer {
            val result = it
            when(result){
                is ResponseManga.Success<Response> -> {
                    adapter = MangasSeinenAdapter(result.data.data,mangaOnclickListener = object : MangaOnclickListener{
                        override fun onClick(manga: Manga) {
                            val bundle = Bundle()
                            bundle.putString(ENVIAR_URL,manga.mangaUrl)

                            Navigation
                                .findNavController(view)
                                .navigate(R.id.action_mainFragment_to_descripcionFragment
                                    ,bundle)
                        }
                    })
                }else -> {}
            }
            binding.rvMangasSeinen.adapter = adapter
        })

        viewModel.getMangasPopulares().observe(viewLifecycleOwner, Observer {
            val result = it
            when(result){
                is ResponseManga.Success<Response> -> {
                    adapterPopulares = MangasPopularesAdapter(result.data.data,mangaOnclickListener = object : MangaOnclickListener{
                        override fun onClick(manga: Manga) {
                            val bundle = Bundle()
                            bundle.putString(ENVIAR_URL,manga.mangaUrl)

                            Navigation
                                .findNavController(view)
                                .navigate(R.id.action_mainFragment_to_descripcionFragment
                                    ,bundle)
                        }
                    })
                }else -> {}
            }
            binding.rvMangasPopulares.adapter = adapterPopulares
        })
    }

    fun showProgressbar(){
        binding.pbCargarMangas.visibility = View.VISIBLE
    }
    fun hideProgressBar(){
        binding.pbCargarMangas.visibility = View.GONE
    }

    fun hideComponents(){
        binding.textView.visibility = View.GONE
        binding.rvMangasSeinen.visibility = View.GONE
        binding.rvMangasPopulares.visibility = View.GONE
        binding.tvSeinen.visibility = View.GONE
    }

    fun showComponents(){
        binding.textView.visibility = View.VISIBLE
        binding.rvMangasSeinen.visibility = View.VISIBLE
        binding.rvMangasPopulares.visibility = View.VISIBLE
        binding.tvSeinen.visibility = View.VISIBLE
    }
}